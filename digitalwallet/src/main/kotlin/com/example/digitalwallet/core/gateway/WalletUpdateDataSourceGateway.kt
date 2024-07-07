package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.WalletDto

interface WalletUpdateDataSourceGateway {

    fun execute(payerWallet: WalletDto, payeeWallet: WalletDto)
}