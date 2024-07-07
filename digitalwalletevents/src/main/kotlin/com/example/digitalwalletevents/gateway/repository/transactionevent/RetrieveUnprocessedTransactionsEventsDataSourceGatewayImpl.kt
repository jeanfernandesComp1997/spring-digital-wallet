package com.example.digitalwalletevents.gateway.repository.transactionevent

import com.example.digitalwalletevents.common.mapper.TransactionEventMapper
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.gateway.RetrieveUnprocessedTransactionsEventsDataSourceGateway
import org.springframework.stereotype.Component

@Component
class RetrieveUnprocessedTransactionsEventsDataSourceGatewayImpl(
    private val transactionEventRepository: TransactionEventRepository,
    private val transactionEventMapper: TransactionEventMapper
) : RetrieveUnprocessedTransactionsEventsDataSourceGateway {

    override fun execute(): List<TransactionEventDto> {
        return transactionEventRepository.fetchUnprocessedTransactions().map { transactionEventModel ->
            transactionEventMapper.toDto(transactionEventModel)
        }
    }
}