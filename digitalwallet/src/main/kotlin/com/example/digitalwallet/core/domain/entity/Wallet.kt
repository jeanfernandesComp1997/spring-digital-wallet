package com.example.digitalwallet.core.domain.entity

import com.example.digitalwallet.core.domain.enums.UserType
import com.example.digitalwallet.core.domain.exception.ForbiddenTransactionException
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
    var version: Long
        private set

    constructor(id: UUID, user: User, balance: BigDecimal, version: Long) {
        this.id = id
        this.user = user
        this.balance = balance
        this.version = version
    }

    constructor(user: User) {
        this.id = UUID.randomUUID()
        this.user = user
        this.balance = BigDecimal(0)
        this.version = 0
    }

    fun debit(value: BigDecimal) {
        checkIfDebitIsAllowedByUserType()
        checkIfTransferValueIsValid(value)
        checkIfThereSufficientBalanceForTransfer(value)
        balance -= value
    }

    fun credit(value: BigDecimal) {
        if (value <= BigDecimal(0)) throw InvalidTransactionValueException()
        balance += value
    }

    fun checkHasBalance() {
        if (balance < BigDecimal(0))
            throw InsufficientBalanceException()
    }

    private fun checkIfThereSufficientBalanceForTransfer(value: BigDecimal) {
        if (balance < value)
            throw InsufficientBalanceException()
    }

    private fun checkIfTransferValueIsValid(value: BigDecimal) {
        if (value <= BigDecimal(0))
            throw InvalidTransactionValueException()
    }

    private fun checkIfDebitIsAllowedByUserType() {
        if (user.type == UserType.SHOP_KEEPER)
            throw ForbiddenTransactionException()
    }
}