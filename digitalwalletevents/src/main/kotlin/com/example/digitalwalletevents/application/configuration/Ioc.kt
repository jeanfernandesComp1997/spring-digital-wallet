package com.example.digitalwalletevents.application.configuration

import com.example.digitalwalletevents.core.gateway.RetrieveUnprocessedTransactionsEventsDataSourceGateway
import com.example.digitalwalletevents.core.usecase.RetrieveUnprocessedTransactionsEventsUseCase
import com.example.digitalwalletevents.core.usecase.impl.RetrieveUnprocessedTransactionsEventsUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Ioc {

    @Bean
    fun retrieveUnprocessedTransactionsEventsUseCase(
        retrieveUnprocessedTransactionsEventsDataSourceGateway: RetrieveUnprocessedTransactionsEventsDataSourceGateway
    ): RetrieveUnprocessedTransactionsEventsUseCase {
        return RetrieveUnprocessedTransactionsEventsUseCaseImpl(
            retrieveUnprocessedTransactionsEventsDataSourceGateway
        )
    }
}