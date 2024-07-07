package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.TransactionDto

interface RegisterTransactionDataSourceGateway {

    fun execute(transaction: TransactionDto)
}