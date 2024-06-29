package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.entity.Wallet

interface WalletMapper {

    fun toEntity(wallet: WalletDto): Wallet

    fun toDto(wallet: Wallet): WalletDto
}