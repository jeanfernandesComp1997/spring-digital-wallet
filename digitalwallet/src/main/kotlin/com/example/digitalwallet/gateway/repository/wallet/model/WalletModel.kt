package com.example.digitalwallet.gateway.repository.wallet.model

import com.example.digitalwallet.gateway.repository.user.model.UserModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "wallet")
data class WalletModel(
    @Id
    val id: UUID,
    @OneToOne
    val user: UserModel,
    val balance: BigDecimal,
    @Version
    val version: Long
)