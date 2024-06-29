package com.example.digitalwallet.gateway.repository.wallet

import com.example.digitalwallet.gateway.repository.wallet.model.WalletModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface WalletRepository : JpaRepository<WalletModel, UUID> {

    fun findByUserId(userId: UUID): Optional<WalletModel>
}