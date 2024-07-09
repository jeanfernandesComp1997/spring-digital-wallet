package com.example.digitalwalletevents.application.message.consumer

import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.usecase.SendTransactionNotificationUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class TransactionEventConsumer(
    private val sendTransactionNotificationUseCase: SendTransactionNotificationUseCase
) {

    val logger: Logger = LoggerFactory.getLogger(TransactionEventConsumer::class.java)
    private val objectMapper = ObjectMapper().registerModule(JavaTimeModule()).registerKotlinModule()

    @RabbitListener(queues = ["transaction-event-queue"])
    @Loggable
    fun consumeTransactionEventsFromQueue(@Payload message: Message<String>) {
        val payload = message.payload
        val transactionEvent = objectMapper.readValue<TransactionEventDto>(payload)
        logger.info("Transaction received, id: {}", transactionEvent.id)
        sendTransactionNotificationUseCase.execute(transactionEvent)
    }
}