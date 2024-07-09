package com.example.digitalwalletevents.core.usecase

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto

interface SendTransactionNotificationUseCase {

    fun execute(transactionEvent: TransactionEventDto)
}