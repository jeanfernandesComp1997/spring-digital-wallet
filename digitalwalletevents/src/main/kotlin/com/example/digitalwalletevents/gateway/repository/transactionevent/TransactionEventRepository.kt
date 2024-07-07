package com.example.digitalwalletevents.gateway.repository.transactionevent

import com.example.digitalwalletevents.gateway.repository.transactionevent.model.TransactionEventModel
import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import java.util.UUID
import org.hibernate.LockMode
import org.hibernate.cfg.AvailableSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints

interface TransactionEventRepository : JpaRepository<TransactionEventModel, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TransactionEventModel t WHERE t.processed = FALSE ORDER BY t.date ASC LIMIT 2")
    @QueryHints(value = [QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "-2")])
    fun fetchUnprocessedTransactions(): List<TransactionEventModel>
}