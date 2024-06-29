package com.example.digitalwallet.gateway.http.authorizerservice.response

data class TransactionAuthorizationResponse(
    val status: String,
    val data: TransactionAuthorizationDataResponse
) {

    data class TransactionAuthorizationDataResponse(
        val authorization: Boolean
    )
}