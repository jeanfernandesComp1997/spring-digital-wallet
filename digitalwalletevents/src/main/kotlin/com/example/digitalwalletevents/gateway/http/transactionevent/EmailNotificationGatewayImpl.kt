package com.example.digitalwalletevents.gateway.http.transactionevent

import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.domain.enums.TransactionEventType
import com.example.digitalwalletevents.core.domain.exception.InvalidEmailNotificationException
import com.example.digitalwalletevents.core.gateway.EmailNotificationGateway
import com.example.digitalwalletevents.gateway.http.transactionevent.request.EmailNotifyRequest
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class EmailNotificationGatewayImpl(
    private val restClient: RestClient,
    @Value("\${external-services.transaction-notify.base-uri}") private val baseUri: String,
    @Value("\${external-services.transaction-notify.from-email}") private val fromEmail: String
) : EmailNotificationGateway {

    private val logger: Logger = LoggerFactory.getLogger(EmailNotificationGatewayImpl::class.java)

    @CircuitBreaker(name = "transaction-notify-gateway")
    @Loggable
    override fun postNotification(transactionEventDto: TransactionEventDto, notificationMessage: String) {
        restClient
            .post()
            .uri("$baseUri/v1/notify")
            .body(createEmailNotifyRequest(transactionEventDto, notificationMessage))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                logger.error(
                    "TransactionEvent email notification id: {} processed with error! statusCode: {}, message: {}",
                    transactionEventDto.id,
                    response.statusCode,
                    response.body.toString()
                )
                if (response.statusCode.isSameCodeAs(HttpStatus.BAD_REQUEST))
                    throw InvalidEmailNotificationException()
            }
            .onStatus(HttpStatusCode::is2xxSuccessful) { _, _ ->
                logger.info(
                    "TransactionEvent email notification id: {} processed with success!",
                    transactionEventDto.id
                )
            }
            .toBodilessEntity()
    }

    private fun createEmailNotifyRequest(
        transactionEventDto: TransactionEventDto,
        notificationMessage: String
    ): EmailNotifyRequest {
        return EmailNotifyRequest(
            from = fromEmail,
            to = retrieveToEmail(transactionEventDto),
            message = notificationMessage
        )
    }

    private fun retrieveToEmail(transactionEventDto: TransactionEventDto): String {
        return if (transactionEventDto.transactionEventType == TransactionEventType.PAYER) {
            transactionEventDto.payerEmail
        } else {
            transactionEventDto.payeeEmail
        }
    }
}