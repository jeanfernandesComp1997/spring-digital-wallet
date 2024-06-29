package com.example.digitalwallet.core.domain.entity

import com.example.digitalwallet.core.domain.exception.InsufficientBalanceException
import com.example.digitalwallet.core.domain.exception.InvalidTransactionValueException
import java.math.BigDecimal
import java.util.UUID

class Wallet {

    var id: UUID
        private set
    var user: User
        private set
    var balance: BigDecimal
        private set

    constructor(user: User) {
        this.id = UUID.randomUUID()
        this.user = user
        this.balance = BigDecimal(0)
    }

    constructor(id: String, user: User, balance: BigDecimal) {
        this.id = UUID.fromString(id)
        this.user = user
        this.balance = balance
    }

    fun debit(value: BigDecimal) {
        checkIfTransferValueIsValid(value)
        checkIfThereSufficientBalanceForTransfer(value)
        balance -= value
    }

    fun credit(value: BigDecimal) {
        if (value <= BigDecimal(0)) throw InvalidTransactionValueException()
        balance += value
    }

    fun checkIfThereSufficientBalanceForTransfer(value: BigDecimal) {
        if (balance < value)
            throw InsufficientBalanceException()
    }

    private fun checkIfTransferValueIsValid(value: BigDecimal) {
        if (value <= BigDecimal(0))
            throw InvalidTransactionValueException()
    }
}