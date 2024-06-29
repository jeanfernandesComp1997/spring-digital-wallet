package com.example.digitalwallet.core.usecase.impl

import com.example.digitalwallet.common.mapper.TransactionEntityMapper
import com.example.digitalwallet.common.mapper.WalletEntityMapper
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.entity.Transaction
import com.example.digitalwallet.core.domain.entity.Wallet
import com.example.digitalwallet.core.domain.exception.ExternalTransactionAuthorizerDeniedException
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletFindByUserIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletUpdateDataSourceGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionDataSourceGateway
import com.example.digitalwallet.core.usecase.TransferFundsUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.UUID

@Service
class TransferFundsUseCaseImpl(
    private val userFindByIdDataSourceGateway: UserFindByIdDataSourceGateway,
    private val walletFindByUserIdDataSourceGateway: WalletFindByUserIdDataSourceGateway,
    private val walletUpdateDataSourceGateway: WalletUpdateDataSourceGateway,
    private val externalTransactionAuthorizerGateway: ExternalTransactionAuthorizerGateway,
    private val registerTransactionDataSourceGateway: RegisterTransactionDataSourceGateway,
    private val walletEntityMapper: WalletEntityMapper,
    private val transactionEntityMapper: TransactionEntityMapper
) : TransferFundsUseCase {

    @Transactional
    override fun execute(input: TransferInputDto): TransactionDto {
        val (payerDto, payeeDto) = userFindByIdDataSourceGateway.execute(input.payer, input.payee)
        val (payerWalletDto, payeeWalletDto) = walletFindByUserIdDataSourceGateway.execute(payerDto.id, payeeDto.id)

        val payerWallet = walletEntityMapper.toEntity(payerWalletDto)
        payerWallet.debit(input.value)

        val payeeWallet = walletEntityMapper.toEntity(payeeWalletDto)
        payeeWallet.credit(input.value)

        checkIfAuthorizedToPerformTransfer(payerDto, payeeDto)

        return makeTransactions(payerWallet, payeeWallet, input)
    }

    fun makeTransactions(
        payerWallet: Wallet,
        payeeWallet: Wallet,
        input: TransferInputDto,
    ): TransactionDto {
        val transaction = Transaction(payerWallet, payeeWallet, input.value)
        val transactionDto = transactionEntityMapper.toDto(transaction)

        walletUpdateDataSourceGateway.execute(
            payerWallet = walletEntityMapper.toDto(payerWallet),
            payeeWallet = walletEntityMapper.toDto(payeeWallet)
        )
        registerTransactionDataSourceGateway.execute(transactionDto)

        checkIfPayerStillHaveBalance(payerWallet.id)

        return transactionDto
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
}