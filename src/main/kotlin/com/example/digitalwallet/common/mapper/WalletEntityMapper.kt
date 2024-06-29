package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.entity.User
import com.example.digitalwallet.core.domain.entity.Wallet
import org.springframework.stereotype.Component

@Component
class WalletEntityMapper {

    fun toEntity(walletDto: WalletDto): Wallet {
        return Wallet(
            id = walletDto.id,
            user = User(
                id = walletDto.user.id,
                firstName = walletDto.user.firstName,
                lastName = walletDto.user.lastName,
                email = walletDto.user.email.address,
                type = walletDto.user.type.name,
                document = walletDto.user.document.value,
                password = walletDto.user.password
            ),
            balance = walletDto.balance
        )
    }

    fun toDto(wallet: Wallet): WalletDto {
        return WalletDto(
            id = wallet.id,
            user = UserDto(
                id = wallet.user.id,
                firstName = wallet.user.firstName,
                lastName = wallet.user.lastName,
                email = wallet.user.email,
                type = wallet.user.type,
                document = wallet.user.document,
                password = wallet.user.password
            ),
            balance = wallet.balance,
        )
    }
}