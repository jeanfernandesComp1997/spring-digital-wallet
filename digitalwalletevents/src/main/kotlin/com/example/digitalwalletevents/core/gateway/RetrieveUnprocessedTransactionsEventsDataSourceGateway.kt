package com.example.digitalwalletevents.core.gateway

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface RetrieveUnprocessedTransactionsEventsDataSourceGateway {

    fun execute(): List<TransactionEventDto>
}