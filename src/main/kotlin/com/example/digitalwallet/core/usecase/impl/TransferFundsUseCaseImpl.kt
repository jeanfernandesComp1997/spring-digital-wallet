package com.example.digitalwallet.core.usecase.impl

import com.example.digitalwallet.common.mapper.TransactionEntityMapper
import com.example.digitalwallet.common.mapper.WalletEntityMapper
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransactionEventDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.entity.Transaction
import com.example.digitalwallet.core.domain.entity.Wallet
import com.example.digitalwallet.core.domain.exception.ExternalTransactionAuthorizerDeniedException
import com.example.digitalwallet.core.domain.exception.InvalidUsersForTransferException
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionDataSourceGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionEventDataSourceGateway
import com.example.digitalwallet.core.gateway.TransactionManagementGateway
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletFindByUserIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletUpdateDataSourceGateway
import com.example.digitalwallet.core.usecase.TransferFundsUseCase
import java.util.UUID

class TransferFundsUseCaseImpl(
    private val userFindByIdDataSourceGateway: UserFindByIdDataSourceGateway,
    private val walletFindByUserIdDataSourceGateway: WalletFindByUserIdDataSourceGateway,
    private val walletUpdateDataSourceGateway: WalletUpdateDataSourceGateway,
    private val externalTransactionAuthorizerGateway: ExternalTransactionAuthorizerGateway,
    private val registerTransactionDataSourceGateway: RegisterTransactionDataSourceGateway,
    private val walletEntityMapper: WalletEntityMapper,
    private val transactionEntityMapper: TransactionEntityMapper,
    private val transactionManagementGateway: TransactionManagementGateway,
    private val registerTransactionEventDataSourceGateway: RegisterTransactionEventDataSourceGateway
) : TransferFundsUseCase {

    override fun execute(input: TransferInputDto): TransactionDto {
        validateUsersIds(input.payer, input.payee)
        val (payerDto, payeeDto) = userFindByIdDataSourceGateway.execute(input.payer, input.payee)
        val (payerWalletDto, payeeWalletDto) = walletFindByUserIdDataSourceGateway.execute(payerDto.id, payeeDto.id)

        val payerWallet = walletEntityMapper.toEntity(payerWalletDto)
        payerWallet.debit(input.value)

        val payeeWallet = walletEntityMapper.toEntity(payeeWalletDto)
        payeeWallet.credit(input.value)

        checkIfAuthorizedToPerformTransfer(payerDto, payeeDto)

        return makeTransaction(payerWallet, payeeWallet, input)
    }

    private fun makeTransaction(
        payerWallet: Wallet,
        payeeWallet: Wallet,
        input: TransferInputDto,
    ): TransactionDto {
        return transactionManagementGateway.doInTransaction {
            val transaction = Transaction(payerWallet, payeeWallet, input.value)
            val transactionDto = transactionEntityMapper.toDto(transaction)
            val transactionEventDto = TransactionEventDto(
                id = UUID.randomUUID(),
                payerFirstName = payerWallet.user.firstName,
                payeeFirstName = payeeWallet.user.firstName,
                payerEmail = payerWallet.user.email.address,
                payeeEmail = payeeWallet.user.email.address,
                transaction.amount,
                transaction.date
            )

            walletUpdateDataSourceGateway.execute(
                payerWallet = walletEntityMapper.toDto(payerWallet),
                payeeWallet = walletEntityMapper.toDto(payeeWallet)
            )
            registerTransactionDataSourceGateway.execute(transactionDto)
            registerTransactionEventDataSourceGateway.execute(transactionEventDto)

            checkIfPayerStillHaveBalance(payerWallet.id)
            transactionDto
        }
    }

    private fun checkIfPayerStillHaveBalance(payerWalletId: UUID) {
        val wallet = walletFindByUserIdDataSourceGateway
            .execute(payerWalletId).let { payerWalletDto ->
                walletEntityMapper.toEntity(payerWalletDto)
            }
        wallet.checkHasBalance()
    }

    private fun checkIfAuthorizedToPerformTransfer(payer: UserDto, payee: UserDto) {
        if (!externalTransactionAuthorizerGateway.checkTransactionAuthorization(
                payer.id.toString(),
                payee.id.toString()
            ).isAuthorized
        ) {
            throw ExternalTransactionAuthorizerDeniedException()
        }
    }

    private fun validateUsersIds(payerId: UUID, payeeId: UUID) {
        if (payerId == payeeId) throw InvalidUsersForTransferException()
    }
}