package com.example.digitalwalletevents.core.domain.entity

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

class TransactionEvent(
    val id: UUID,
    val payerFirstName: String,
    val payeeFirstName: String,
    val payerEmail: String,
    val payeeEmail: String,
    val amount: BigDecimal,
    val date: ZonedDateTime
) {

    var processed: Boolean = false
        private set

    constructor(
        id: UUID,
        payerFirstName: String,
        payeeFirstName: String,
        payerEmail: String,
        payeeEmail: String,
        amount: BigDecimal,
        date: ZonedDateTime,
        processed: Boolean
    ) : this(
        id,
        payerFirstName,
        payeeFirstName,
        payerEmail,
        payeeEmail,
        amount,
        date,
    ) {
        this.processed = processed
    }

    fun setToProcessed() {
        this.processed = true
    }
}