package com.example.digitalwallet.gateway.repository.wallet

import com.example.digitalwallet.gateway.repository.wallet.model.WalletModel
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository : JpaRepository<WalletModel, UUID> {

    fun findByUserId(userId: UUID): Optional<WalletModel>
}