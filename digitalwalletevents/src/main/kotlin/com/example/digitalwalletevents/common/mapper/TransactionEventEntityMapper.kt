package com.example.digitalwalletevents.common.mapper

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.domain.entity.TransactionEvent
import org.springframework.stereotype.Component

@Component
class TransactionEventEntityMapper {

    fun toEntity(transactionEventDto: TransactionEventDto): TransactionEvent {
        return TransactionEvent(
            transactionEventDto.id,
            transactionEventDto.payerFirstName,
            transactionEventDto.payeeFirstName,
            transactionEventDto.payerEmail,
            transactionEventDto.payeeEmail,
            transactionEventDto.amount,
            transactionEventDto.date,
            transactionEventDto.transactionEventType,
            transactionEventDto.sent,
            transactionEventDto.processed
        )
    }

    fun toDto(transactionEvent: TransactionEvent): TransactionEventDto {
        return TransactionEventDto(
            transactionEvent.id,
            transactionEvent.payerFirstName,
            transactionEvent.payeeFirstName,
            transactionEvent.payerEmail,
            transactionEvent.payeeEmail,
            transactionEvent.amount,
            transactionEvent.date,
            transactionEvent.transactionEventType,
            transactionEvent.sent,
            transactionEvent.processed
        )
    }
}