package com.example.digitalwalletevents.core.usecase

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface RetrieveUnprocessedTransactionsEventsUseCase {

    fun execute(): List<TransactionEventDto>
}