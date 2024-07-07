package com.example.digitalwalletevents.application.message.producer.transactionevent

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.usecase.RetrieveUnprocessedTransactionsEventsUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1")
class TransactionEventProducer(
    private val retrieveUnprocessedTransactionsEventsUseCase: RetrieveUnprocessedTransactionsEventsUseCase
) {

    @Scheduled(fixedRate = 1000)
    @Transactional
    fun publishTransactionsEventsOnQueue() {
        val events = retrieveUnprocessedTransactionsEventsUseCase.execute()

        events.forEach { println("${it.id} - ${it.date}") }

        Thread.sleep(20000)
    }

    @Transactional
    @GetMapping("events")
    fun test(): List<TransactionEventDto> {
        return retrieveUnprocessedTransactionsEventsUseCase.execute()
    }
}