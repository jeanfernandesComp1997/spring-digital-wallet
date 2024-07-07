package com.example.digitalwalletevents.core.usecase

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface DeleteProcessedTransactionsEventsUseCase {

    suspend fun execute(transactionEvent: TransactionEventDto)
}