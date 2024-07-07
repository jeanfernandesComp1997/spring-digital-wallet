package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.entity.User
import com.example.digitalwallet.core.domain.entity.Wallet
import org.springframework.stereotype.Component

@Component
class WalletEntityMapper {

    fun toEntity(wallet: WalletDto): Wallet {
        return Wallet(
            id = wallet.id,
            user = User(
                id = wallet.user.id,
                firstName = wallet.user.firstName,
                lastName = wallet.user.lastName,
                email = wallet.user.email.address,
                type = wallet.user.type.name,
                document = wallet.user.document.value,
                password = wallet.user.password
            ),
            balance = wallet.balance,
            version = wallet.version
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
            version = wallet.version
        )
    }
}