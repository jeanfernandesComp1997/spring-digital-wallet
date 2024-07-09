package com.example.digitalwallet.core.domain.dto

import com.example.digitalwallet.core.domain.enums.TransactionEventType
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class TransactionEventDto(
    val id: UUID,
    val payerFirstName: String,
    val payeeFirstName: String,
    val payerEmail: String,
    val payeeEmail: String,
    val amount: BigDecimal,
    val date: ZonedDateTime,
    val transactionEventType: TransactionEventType,
    val sent: Boolean,
    val processed: Boolean
)