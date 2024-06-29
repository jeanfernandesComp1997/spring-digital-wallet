package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.TransactionAuthorizationDto

interface ExternalTransactionAuthorizerGateway {

    fun checkTransactionAuthorization(payerId: String, payeeId: String): TransactionAuthorizationDto
}