package com.example.digitalwalletevents.core.domain.entity

import com.example.digitalwalletevents.core.domain.enums.TransactionEventType
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
    val date: ZonedDateTime,
    val transactionEventType: TransactionEventType
) {

    var sent: Boolean = false
        private set

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
        transactionEventType: TransactionEventType,
        sent: Boolean,
        processed: Boolean
    ) : this(
        id,
        payerFirstName,
        payeeFirstName,
        payerEmail,
        payeeEmail,
        amount,
        date,
        transactionEventType
    ) {
        this.sent = sent
        this.processed = processed
    }

    fun setToProcessed() {
        this.processed = true
    }

    fun setToSent() {
        this.sent = true
    }

    fun retrieveNotificationMessage(): String {
        val notificationMessage = StringBuilder()
        notificationMessage.append("Hello, ${retrieveFirstName()}!")
        notificationMessage.append(retrieveMessage())
        return notificationMessage.toString()
    }

    private fun retrieveFirstName(): String {
        return if (transactionEventType == TransactionEventType.PAYER) {
            payerFirstName
        } else {
            payeeFirstName
        }
    }

    private fun retrieveMessage(): String {
        return if (transactionEventType == TransactionEventType.PAYER) {
            "You just made a transaction for $payeeFirstName worth \$$amount at $date"
        } else {
            "You just received a transaction from $payerFirstName worth \$$amount at $date"
        }
    }
}