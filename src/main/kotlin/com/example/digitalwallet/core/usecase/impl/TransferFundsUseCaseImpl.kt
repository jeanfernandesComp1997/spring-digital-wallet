package com.example.digitalwallet.core.usecase.impl

import com.example.digitalwallet.common.mapper.TransactionMapper
import com.example.digitalwallet.common.mapper.WalletMapper
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import com.example.digitalwallet.core.domain.entity.Transaction
import com.example.digitalwallet.core.domain.exception.ExternalTransactionAuthorizerDeniedException
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletFindByUserIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletUpdateDataSourceGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionDataSourceGateway
import com.example.digitalwallet.core.usecase.TransferFundsUseCase

class TransferFundsUseCaseImpl(
    private val userFindByIdDataSourceGateway: UserFindByIdDataSourceGateway,
    private val walletFindByUserIdDataSourceGateway: WalletFindByUserIdDataSourceGateway,
    private val walletUpdateDataSourceGateway: WalletUpdateDataSourceGateway,
    private val externalTransactionAuthorizerGateway: ExternalTransactionAuthorizerGateway,
    private val registerTransactionDataSourceGateway: RegisterTransactionDataSourceGateway,
    private val walletMapper: WalletMapper,
    private val transactionMapper: TransactionMapper
) : TransferFundsUseCase {

    override fun execute(input: TransferInputDto): TransactionDto {
        val (payerDto, payeeDto) = userFindByIdDataSourceGateway.execute(input.payer, input.payee)
        var (payerWalletDto, payeeWalletDto) = walletFindByUserIdDataSourceGateway.execute(payerDto.id, payeeDto.id)

        var payerWallet = walletMapper.toEntity(payerWalletDto)
        payerWallet.debit(input.value)

        val payeeWallet = walletMapper.toEntity(payeeWalletDto)
        payeeWallet.credit(input.value)

        if (!externalTransactionAuthorizerGateway.checkTransactionAuthorization().isAuthorized)
            throw ExternalTransactionAuthorizerDeniedException()

        payerWalletDto = walletMapper.toDto(payerWallet)
        payeeWalletDto = walletMapper.toDto(payeeWallet)

        walletUpdateDataSourceGateway.execute(payerWalletDto, payeeWalletDto)

        val transaction = Transaction(payerWallet.id, payerWallet.id, input.value)
        val transactionDto = transactionMapper.toDto(transaction)
        registerTransactionDataSourceGateway.execute(transactionDto)

        payerWalletDto = walletFindByUserIdDataSourceGateway.execute(payerDto.id)
        payerWallet = walletMapper.toEntity(payerWalletDto)

        payerWallet.checkIfThereSufficientBalanceForTransfer(input.value)

        return transactionDto
    }
}