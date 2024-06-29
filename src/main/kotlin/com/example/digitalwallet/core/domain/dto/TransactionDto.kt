package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class TransactionDto(
    val id: UUID,
    val payer: UUID,
    val payee: UUID,
    val amount: BigDecimal,
    val date: ZonedDateTime
)