package com.example.digitalwalletevents.core.usecase.impl

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.gateway.RetrieveUnprocessedTransactionsEventsDataSourceGateway
import com.example.digitalwalletevents.core.usecase.RetrieveUnprocessedTransactionsEventsUseCase

class RetrieveUnprocessedTransactionsEventsUseCaseImpl(
    private val retrieveUnprocessedTransactionsEventsDataSourceGateway: RetrieveUnprocessedTransactionsEventsDataSourceGateway
) : RetrieveUnprocessedTransactionsEventsUseCase {

    override fun execute(): List<TransactionEventDto> {
        return retrieveUnprocessedTransactionsEventsDataSourceGateway.execute()
    }
}