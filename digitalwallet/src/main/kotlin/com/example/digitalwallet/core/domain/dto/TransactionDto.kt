package com.example.digitalwallet.core.domain.dto

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class TransactionDto(
    val id: UUID,
    val payerWallet: WalletDto,
    val payeeWallet: WalletDto,
    val amount: BigDecimal,
    val date: ZonedDateTime
)