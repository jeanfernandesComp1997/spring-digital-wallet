package com.example.digitalwalletevents.application.configuration

import com.example.digitalwalletevents.common.mapper.TransactionEventEntityMapper
import com.example.digitalwalletevents.core.gateway.PostTransactionEventQueueGateway
import com.example.digitalwalletevents.core.gateway.RetrieveUnprocessedTransactionsEventsDataSourceGateway
import com.example.digitalwalletevents.core.gateway.UpdateTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.usecase.PostTransactionEventsUseCase
import com.example.digitalwalletevents.core.usecase.impl.PostTransactionEventsUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Ioc {

    @Bean
    fun postTransactionEventsUseCase(
        retrieveUnprocessedTransactionsEventsDataSourceGateway: RetrieveUnprocessedTransactionsEventsDataSourceGateway,
        updateTransactionEventDataSourceGateway: UpdateTransactionEventDataSourceGateway,
        transactionEventEntityMapper: TransactionEventEntityMapper,
        postTransactionEventQueueGateway: PostTransactionEventQueueGateway
    ): PostTransactionEventsUseCase {
        return PostTransactionEventsUseCaseImpl(
            retrieveUnprocessedTransactionsEventsDataSourceGateway,
            updateTransactionEventDataSourceGateway,
            transactionEventEntityMapper,
            postTransactionEventQueueGateway
        )
    }
}