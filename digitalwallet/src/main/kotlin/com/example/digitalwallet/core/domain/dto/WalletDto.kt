package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal
import java.util.UUID

data class WalletDto(
    val id: UUID,
    val user: UserDto,
    val balance: BigDecimal,
    val version: Long
)
