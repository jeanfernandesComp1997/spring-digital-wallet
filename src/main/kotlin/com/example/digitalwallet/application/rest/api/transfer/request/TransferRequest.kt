package com.example.digitalwallet.application.rest.api.transfer.request

import com.example.digitalwallet.application.rest.api.validators.annotations.ValidTransferValue
import java.math.BigDecimal
import java.util.UUID

data class TransferRequest(
    val payer: UUID,
    val payee: UUID,
    @ValidTransferValue
    val value: BigDecimal
)