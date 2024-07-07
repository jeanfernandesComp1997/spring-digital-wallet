package com.example.digitalwallet.gateway.repository.transactionevent.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "transaction_event")
data class TransactionEventModel(
    @Id
    val id: UUID,
    @Column(name = "payer_name")
    val payerFirstName: String,
    @Column(name = "payee_name")
    val payeeFirstName: String,
    @Column(name = "payer_email")
    val payerEmail: String,
    @Column(name = "payee_email")
    val payeeEmail: String,
    val amount: BigDecimal,
    val date: ZonedDateTime,
    val processed: Boolean
)
