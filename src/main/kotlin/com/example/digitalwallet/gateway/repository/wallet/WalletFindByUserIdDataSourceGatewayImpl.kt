package com.example.digitalwallet.gateway.repository.wallet

import com.example.digitalwallet.common.mapper.WalletMapper
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.exception.WalletNotFoundException
import com.example.digitalwallet.core.gateway.WalletFindByUserIdDataSourceGateway
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class WalletFindByUserIdDataSourceGatewayImpl(
    private val walletRepository: WalletRepository,
    private val walletMapper: WalletMapper
) : WalletFindByUserIdDataSourceGateway {

    override fun execute(userId: UUID): WalletDto {
        val walletModel = walletRepository.findById(userId)
        return if (walletModel.isPresent) {
            walletMapper.toDto(walletModel.get())
        } else {
            throw WalletNotFoundException()
        }
    }

    override fun execute(payerId: UUID, payeeId: UUID): Pair<WalletDto, WalletDto> {
        val payerWallet = walletRepository.findByUserId(payerId)
        val payeeWallet = walletRepository.findByUserId(payeeId)

        return if (payerWallet.isPresent && payeeWallet.isPresent) {
            Pair(first = walletMapper.toDto(payerWallet.get()), second = walletMapper.toDto(payeeWallet.get()))
        } else {
            throw WalletNotFoundException()
        }
    }
}