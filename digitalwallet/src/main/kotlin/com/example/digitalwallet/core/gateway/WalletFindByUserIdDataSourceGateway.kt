package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.WalletDto
import java.util.UUID

interface WalletFindByUserIdDataSourceGateway {

    fun execute(id: UUID): WalletDto
    fun execute(payerId: UUID, payeeId: UUID): Pair<WalletDto, WalletDto>
}