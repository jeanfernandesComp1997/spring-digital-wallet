package com.example.digitalwalletevents.core.gateway

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface UpdateTransactionEventDataSourceGateway {

    fun execute(transactionEvent: TransactionEventDto)
}