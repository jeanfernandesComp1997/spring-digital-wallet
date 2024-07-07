package com.example.digitalwallet.core.gateway

interface TransactionManagementGateway {

    fun <T> doInTransaction(actions: () -> T): T
}