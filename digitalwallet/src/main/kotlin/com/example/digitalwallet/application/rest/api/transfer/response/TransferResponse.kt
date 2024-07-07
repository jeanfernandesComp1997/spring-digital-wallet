package com.example.digitalwallet.application.rest.api.transfer.response

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class TransferResponse(
    val id: UUID,
    val payer: UUID,
    val payee: UUID,
    val amount: BigDecimal,
    val date: ZonedDateTime
)