package com.example.digitalwalletevents.application.message.producer.transactionevent

import com.example.digitalwalletevents.core.usecase.PostTransactionEventsUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionEventProducer(
    private val postTransactionEventsUseCase: PostTransactionEventsUseCase
) {

    @Scheduled(fixedRate = 1000)
    @Transactional
    fun publishTransactionsEventsOnQueue() {
        postTransactionEventsUseCase.execute()
    }
}