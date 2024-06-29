package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal
import java.util.UUID

data class TransferInputDto(
    val payer: UUID,
    val payee: UUID,
    val value: BigDecimal
)