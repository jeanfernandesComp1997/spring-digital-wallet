package com.example.digitalwallet.gateway.repository.transaction

import com.example.digitalwallet.gateway.repository.transaction.model.TransactionModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TransactionRepository : JpaRepository<TransactionModel, UUID>