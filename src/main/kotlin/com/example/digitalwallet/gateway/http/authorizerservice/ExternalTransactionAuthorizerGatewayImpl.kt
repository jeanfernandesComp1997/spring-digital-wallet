package com.example.digitalwallet.gateway.http.authorizerservice

import com.example.digitalwallet.common.mapper.TransactionAuthorizationMapper
import com.example.digitalwallet.core.domain.dto.TransactionAuthorizationDto
import com.example.digitalwallet.core.gateway.ExternalTransactionAuthorizerGateway
import com.example.digitalwallet.gateway.http.authorizerservice.response.TransactionAuthorizationResponse
import org.springframework.beans.factory.annotation.Value
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

    override fun checkTransactionAuthorization(payerId: String, payeeId: String): TransactionAuthorizationDto {
        val response = restClient
            .get()
            .uri("$baseUri/v2/authorize")
//            .headers {
//                it.add(PAYER_ID_HEADER_NAME, payerId)
//                it.add(PAYEE_ID_HEADER_NAME, payeeId)
//            }
            .retrieve()
            .body(TransactionAuthorizationResponse::class.java)
            ?: throw RuntimeException("Cannot convert the rest client response.")
        return transactionAuthorizationMapper.toDto(response)
    }
}