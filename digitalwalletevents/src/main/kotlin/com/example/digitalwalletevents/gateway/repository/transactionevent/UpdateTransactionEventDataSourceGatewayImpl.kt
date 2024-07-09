package com.example.digitalwalletevents.gateway.repository.transactionevent

import com.example.digitalwalletevents.common.mapper.TransactionEventMapper
import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.gateway.UpdateTransactionEventDataSourceGateway
import org.springframework.stereotype.Component

@Component
class UpdateTransactionEventDataSourceGatewayImpl(
    private val transactionEventRepository: TransactionEventRepository,
    private val transactionEventMapper: TransactionEventMapper
) : UpdateTransactionEventDataSourceGateway {

    @Loggable
    override fun execute(transactionEvent: TransactionEventDto) {
        val transactionModel = transactionEventMapper.toModel(transactionEvent)
        transactionEventRepository.save(transactionModel)
    }
}