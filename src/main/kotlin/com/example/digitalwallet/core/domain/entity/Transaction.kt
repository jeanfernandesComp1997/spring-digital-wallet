package com.example.digitalwallet.core.domain.entity

import jakarta.transaction.InvalidTransactionException
import java.math.BigDecimal
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.UUID

class Transaction {

    var id: UUID
        private set
    var payerWallet: Wallet
        private set
    var payeeWallet: Wallet
        private set
    var amount: BigDecimal
        private set
    var date: ZonedDateTime
        private set

    constructor(payerWallet: Wallet, payeeWallet: Wallet, amount: BigDecimal) {
        require(amount > BigDecimal(0)) { InvalidTransactionException() }
        this.id = UUID.randomUUID()
        this.payerWallet = payerWallet
        this.payeeWallet = payeeWallet
        this.amount = amount
        this.date = ZonedDateTime.now(ZoneOffset.UTC)
    }

    constructor(id: UUID, payerWallet: Wallet, payeeWallet: Wallet, amount: BigDecimal, date: ZonedDateTime) {
        require(amount > BigDecimal(0)) { InvalidTransactionException() }
        this.id = id
        this.payerWallet = payerWallet
        this.payeeWallet = payeeWallet
        this.amount = amount
        this.date = date
    }
}