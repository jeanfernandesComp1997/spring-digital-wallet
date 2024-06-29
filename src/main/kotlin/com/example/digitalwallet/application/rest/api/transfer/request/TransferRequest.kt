package com.example.digitalwallet.application.rest.api.transfer.request

import java.math.BigDecimal
import java.util.UUID

data class TransferRequest(
    val value: BigDecimal,
    val payer: UUID,
    val payee: UUID
)
