package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.TransactionEventDto

interface RegisterTransactionEventDataSourceGateway {

    fun execute(transactionEvent: TransactionEventDto)
}