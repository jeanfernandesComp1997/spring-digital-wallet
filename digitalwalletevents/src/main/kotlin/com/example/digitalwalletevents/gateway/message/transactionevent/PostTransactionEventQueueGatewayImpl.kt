package com.example.digitalwalletevents.gateway.message.transactionevent

import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.gateway.PostTransactionEventQueueGateway
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessageProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PostTransactionEventQueueGatewayImpl(
    private val amqpTemplate: AmqpTemplate,
    @Value("\${transaction-event.exchange}") private val transactionEventExchange: String,
    @Value("\${transaction-event.routing-key}") private val transactionEventRoutingKey: String
) : PostTransactionEventQueueGateway {

    private val jsonMapper = JsonMapper().registerModule(JavaTimeModule())

    @Loggable
    override fun execute(transaction: TransactionEventDto) {
        val properties = MessageProperties()
        properties.delayLong = 10000L

        amqpTemplate.convertAndSend(
            transactionEventExchange,
            transactionEventRoutingKey,
            MessageBuilder
                .withBody(jsonMapper.writeValueAsString(transaction).toByteArray())
                .andProperties(properties)
                .build()
        )
    }
}