package com.example.digitalwallet.gateway.repository.transaction.model

import com.example.digitalwallet.gateway.repository.wallet.model.WalletModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "transaction")
data class TransactionModel(
    @Id
    val id: UUID,
    @ManyToOne
    @JoinColumn(name = "payer_wallet_id")
    val payerWallet: WalletModel,
    @ManyToOne
    @JoinColumn(name = "payee_wallet_id")
    val payeeWallet: WalletModel,
    val amount: BigDecimal,
    val date: ZonedDateTime
)