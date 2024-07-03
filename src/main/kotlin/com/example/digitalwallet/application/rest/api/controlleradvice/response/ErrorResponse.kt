package com.example.digitalwallet.application.rest.api.controlleradvice.response

import org.springframework.http.HttpStatusCode

data class ErrorResponse(
    val code: HttpStatusCode,
    val message: String,
    val links: List<String>? = listOf()
)
