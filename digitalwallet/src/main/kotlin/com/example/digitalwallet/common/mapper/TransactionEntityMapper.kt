package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.entity.Transaction
import org.springframework.stereotype.Component

@Component
class TransactionEntityMapper {

    fun toDto(transaction: Transaction): TransactionDto {
        return TransactionDto(
            id = transaction.id,
            payerWallet = WalletDto(
                id = transaction.payerWallet.id,
                user = UserDto(
                    id = transaction.payerWallet.user.id,
                    firstName = transaction.payerWallet.user.firstName,
                    lastName = transaction.payerWallet.user.lastName,
                    email = transaction.payerWallet.user.email,
                    type = transaction.payerWallet.user.type,
                    document = transaction.payerWallet.user.document,
                    password = transaction.payerWallet.user.password
                ),
                balance = transaction.payerWallet.balance,
                version = transaction.payerWallet.version
            ),
            payeeWallet = WalletDto(
                id = transaction.payeeWallet.id,
                user = UserDto(
                    id = transaction.payeeWallet.user.id,
                    firstName = transaction.payeeWallet.user.firstName,
                    lastName = transaction.payeeWallet.user.lastName,
                    email = transaction.payeeWallet.user.email,
                    type = transaction.payeeWallet.user.type,
                    document = transaction.payeeWallet.user.document,
                    password = transaction.payeeWallet.user.password
                ),
                balance = transaction.payeeWallet.balance,
                version = transaction.payeeWallet.version
            ),
            amount = transaction.amount,
            date = transaction.date
        )
    }
}