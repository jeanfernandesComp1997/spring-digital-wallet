package com.example.digitalwalletevents.application.configuration

import com.example.digitalwalletevents.common.mapper.TransactionEventEntityMapper
import com.example.digitalwalletevents.core.gateway.EmailNotificationGateway
import com.example.digitalwalletevents.core.gateway.PostTransactionEventQueueGateway
import com.example.digitalwalletevents.core.gateway.RetrieveNotSentTransactionsEventsDataSourceGateway
import com.example.digitalwalletevents.core.gateway.RetrieveTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.gateway.UpdateTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.usecase.PostTransactionEventsUseCase
import com.example.digitalwalletevents.core.usecase.SendTransactionNotificationUseCase
import com.example.digitalwalletevents.core.usecase.impl.PostTransactionEventsUseCaseImpl
import com.example.digitalwalletevents.core.usecase.impl.SendTransactionNotificationUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Ioc {

    @Bean
    fun postTransactionEventsUseCase(
        retrieveNotSentTransactionsEventsDataSourceGateway: RetrieveNotSentTransactionsEventsDataSourceGateway,
        updateTransactionEventDataSourceGateway: UpdateTransactionEventDataSourceGateway,
        transactionEventEntityMapper: TransactionEventEntityMapper,
        postTransactionEventQueueGateway: PostTransactionEventQueueGateway
    ): PostTransactionEventsUseCase {
        return PostTransactionEventsUseCaseImpl(
            retrieveNotSentTransactionsEventsDataSourceGateway,
            updateTransactionEventDataSourceGateway,
            transactionEventEntityMapper,
            postTransactionEventQueueGateway
        )
    }

    @Bean
    fun sendTransactionNotificationUseCase(
        updateTransactionEventDataSourceGateway: UpdateTransactionEventDataSourceGateway,
        emailNotificationGateway: EmailNotificationGateway,
        retrieveTransactionEventDataSourceGateway: RetrieveTransactionEventDataSourceGateway,
        transactionEventEntityMapper: TransactionEventEntityMapper
    ): SendTransactionNotificationUseCase {
        return SendTransactionNotificationUseCaseImpl(
            updateTransactionEventDataSourceGateway,
            emailNotificationGateway,
            retrieveTransactionEventDataSourceGateway,
            transactionEventEntityMapper,
        )
    }
}