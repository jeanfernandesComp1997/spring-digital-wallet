package com.example.digitalwallet.core.usecase.impl

import com.example.digitalwallet.common.mapper.TransactionEntityMapper
import com.example.digitalwallet.common.mapper.WalletEntityMapper
import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransactionEventDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.entity.Transaction
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

open class TransferFundsUseCaseImpl(
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

    @Loggable
    override fun execute(transferInput: TransferInputDto): TransactionDto {
        validateUsersIds(payerId = transferInput.payerId, payeeId = transferInput.payeeId)
        val (payerDto, payeeDto) = userFindByIdDataSourceGateway.execute(
            payerId = transferInput.payerId,
            payeeId = transferInput.payeeId
        )

        checkIfAuthorizedToPerformTransfer(payer = payerDto, payee = payeeDto)

        return makeTransaction(payer = payerDto, payee = payeeDto, transferInput = transferInput)
    }

    private fun makeTransaction(
        payer: UserDto,
        payee: UserDto,
        transferInput: TransferInputDto,
    ): TransactionDto {
        return transactionManagementGateway.doInTransaction {
            val (payerWalletDto, payeeWalletDto) = walletFindByUserIdDataSourceGateway.execute(
                payerId = payer.id,
                payeeId = payee.id
            )

            val payerWallet = walletEntityMapper.toEntity(payerWalletDto)
            payerWallet.debit(transferInput.value)

            val payeeWallet = walletEntityMapper.toEntity(payeeWalletDto)
            payeeWallet.credit(transferInput.value)

            val transaction =
                Transaction(payerWallet = payerWallet, payeeWallet = payeeWallet, amount = transferInput.value)
            val transactionDto = transactionEntityMapper.toDto(transaction)
            val transactionEventDto = TransactionEventDto(
                id = UUID.randomUUID(),
                payerFirstName = payerWallet.user.firstName,
                payeeFirstName = payeeWallet.user.firstName,
                payerEmail = payerWallet.user.email.address,
                payeeEmail = payeeWallet.user.email.address,
                amount = transaction.amount,
                date = transaction.date,
                processed = false
            )

            walletUpdateDataSourceGateway.execute(
                payerWallet = walletEntityMapper.toDto(payerWallet),
                payeeWallet = walletEntityMapper.toDto(payeeWallet)
            )
            registerTransactionDataSourceGateway.execute(transaction = transactionDto)
            registerTransactionEventDataSourceGateway.execute(transactionEvent = transactionEventDto)

            transactionDto
        }
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