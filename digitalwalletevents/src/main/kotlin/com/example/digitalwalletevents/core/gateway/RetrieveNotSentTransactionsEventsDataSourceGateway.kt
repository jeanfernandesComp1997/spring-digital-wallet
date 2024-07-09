package com.example.digitalwalletevents.core.gateway

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface RetrieveNotSentTransactionsEventsDataSourceGateway {

    fun execute(): List<TransactionEventDto>
}