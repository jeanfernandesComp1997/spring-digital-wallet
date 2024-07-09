package com.example.digitalwalletevents.gateway.http.transactionevent.request

data class EmailNotifyRequest(
    val from: String,
    val to: String,
    val message: String
)
