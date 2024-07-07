package com.example.digitalwalletevents.core.usecase.impl

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.usecase.DeleteProcessedTransactionsEventsUseCase

class DeleteProcessedTransactionsEventsUseCaseImpl : DeleteProcessedTransactionsEventsUseCase {

    override suspend fun execute(transactionEvent: TransactionEventDto) {
        TODO("Not yet implemented")
    }
}