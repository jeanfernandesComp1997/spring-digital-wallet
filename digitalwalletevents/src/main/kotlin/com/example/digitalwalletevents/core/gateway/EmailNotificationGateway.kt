package com.example.digitalwalletevents.core.gateway

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface EmailNotificationGateway {

    fun postNotification(transactionEventDto: TransactionEventDto, notificationMessage: String)
}