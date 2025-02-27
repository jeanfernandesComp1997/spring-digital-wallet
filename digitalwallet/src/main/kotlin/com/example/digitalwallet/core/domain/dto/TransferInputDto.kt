package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal
import java.util.UUID

data class TransferInputDto(
    val payerId: UUID,
    val payeeId: UUID,
    val value: BigDecimal
)