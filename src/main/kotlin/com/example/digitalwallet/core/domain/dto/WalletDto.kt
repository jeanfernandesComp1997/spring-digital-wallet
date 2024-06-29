package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal

data class WalletDto(
    val id: String,
    val userId: String,
    val balance: BigDecimal
)
