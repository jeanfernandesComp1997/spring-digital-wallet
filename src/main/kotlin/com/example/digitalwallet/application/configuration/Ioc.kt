package com.example.digitalwallet.application.configuration

import com.example.digitalwallet.common.mapper.TransactionEntityMapper
import com.example.digitalwallet.common.mapper.WalletEntityMapper
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionDataSourceGateway
import com.example.digitalwallet.core.gateway.RegisterTransactionEventDataSourceGateway
import com.example.digitalwallet.core.gateway.TransactionManagementGateway
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletFindByUserIdDataSourceGateway
import com.example.digitalwallet.core.gateway.WalletUpdateDataSourceGateway
import com.example.digitalwallet.core.usecase.TransferFundsUseCase
import com.example.digitalwallet.core.usecase.impl.TransferFundsUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Ioc {

    @Bean
    fun transferFundsUseCase(
        userFindByIdDataSourceGateway: UserFindByIdDataSourceGateway,
        walletFindByUserIdDataSourceGateway: WalletFindByUserIdDataSourceGateway,
        walletUpdateDataSourceGateway: WalletUpdateDataSourceGateway,
        externalTransactionAuthorizerGateway: ExternalTransactionAuthorizerGateway,
        registerTransactionDataSourceGateway: RegisterTransactionDataSourceGateway,
        walletEntityMapper: WalletEntityMapper,
        transactionEntityMapper: TransactionEntityMapper,
        transactionManagementGateway: TransactionManagementGateway,
        registerTransactionEventDataSourceGateway: RegisterTransactionEventDataSourceGateway
    ): TransferFundsUseCase {
        return TransferFundsUseCaseImpl(
            userFindByIdDataSourceGateway,
            walletFindByUserIdDataSourceGateway,
            walletUpdateDataSourceGateway,
            externalTransactionAuthorizerGateway,
            registerTransactionDataSourceGateway,
            walletEntityMapper,
            transactionEntityMapper,
            transactionManagementGateway,
            registerTransactionEventDataSourceGateway
        )
    }
}