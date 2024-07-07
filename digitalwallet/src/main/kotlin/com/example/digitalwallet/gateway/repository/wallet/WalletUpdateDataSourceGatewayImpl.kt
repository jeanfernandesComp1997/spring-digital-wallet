package com.example.digitalwallet.gateway.repository.wallet

import com.example.digitalwallet.common.mapper.WalletMapper
import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.gateway.WalletUpdateDataSourceGateway
import org.springframework.stereotype.Component

@Component
class WalletUpdateDataSourceGatewayImpl(
    private val walletRepository: WalletRepository,
    private val walletMapper: WalletMapper
) : WalletUpdateDataSourceGateway {

    @Loggable
    override fun execute(payerWallet: WalletDto, payeeWallet: WalletDto) {
        val payerWalletModel = walletMapper.toModel(payerWallet)
        val payeeWalletModel = walletMapper.toModel(payeeWallet)

        walletRepository.save(payerWalletModel)
        walletRepository.save(payeeWalletModel)
    }
}