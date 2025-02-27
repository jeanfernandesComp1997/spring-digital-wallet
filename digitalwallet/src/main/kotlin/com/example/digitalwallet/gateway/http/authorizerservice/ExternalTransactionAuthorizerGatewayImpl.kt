package com.example.digitalwallet.gateway.http.authorizerservice

import com.example.digitalwallet.common.mapper.TransactionAuthorizationMapper
import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.domain.dto.TransactionAuthorizationDto
import com.example.digitalwallet.core.domain.exception.ExternalTransactionAuthorizerDeniedException
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.gateway.http.authorizerservice.response.TransactionAuthorizationResponse
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class ExternalTransactionAuthorizerGatewayImpl(
    private val restClient: RestClient,
    private val transactionAuthorizationMapper: TransactionAuthorizationMapper,
    @Value("\${external-services.transaction-authorizer.base-uri}") private val baseUri: String
) : ExternalTransactionAuthorizerGateway {

    companion object {
        const val PAYER_ID_HEADER_NAME = "payerId"
        const val PAYEE_ID_HEADER_NAME = "payeeId"
    }

    @CircuitBreaker(name = "transaction-authorizer-gateway")
    @Loggable
    override fun checkTransactionAuthorization(payerId: String, payeeId: String): TransactionAuthorizationDto {
        val response = restClient
            .get()
            .uri("$baseUri/v2/authorize")
            .headers { httpHeader ->
                httpHeader.add(PAYER_ID_HEADER_NAME, payerId)
                httpHeader.add(PAYEE_ID_HEADER_NAME, payeeId)
            }
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                if (response.statusCode.isSameCodeAs(HttpStatus.FORBIDDEN))
                    throw ExternalTransactionAuthorizerDeniedException()
            }
            .body(TransactionAuthorizationResponse::class.java)
            ?: throw RuntimeException("Cannot convert the rest client response.")
        return transactionAuthorizationMapper.toDto(response)
    }
}