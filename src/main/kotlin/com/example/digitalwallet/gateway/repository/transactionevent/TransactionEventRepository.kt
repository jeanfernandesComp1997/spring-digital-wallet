package com.example.digitalwallet.gateway.repository.transactionevent

import com.example.digitalwallet.gateway.repository.transactionevent.model.TransactionEventModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TransactionEventRepository : JpaRepository<TransactionEventModel, UUID>