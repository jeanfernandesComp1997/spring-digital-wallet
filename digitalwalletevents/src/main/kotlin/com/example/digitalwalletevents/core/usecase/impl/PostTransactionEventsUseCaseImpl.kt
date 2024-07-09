package com.example.digitalwalletevents.core.usecase.impl

import com.example.digitalwalletevents.common.mapper.TransactionEventEntityMapper
import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.gateway.PostTransactionEventQueueGateway
import com.example.digitalwalletevents.core.gateway.RetrieveUnprocessedTransactionsEventsDataSourceGateway
import com.example.digitalwalletevents.core.gateway.UpdateTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.usecase.PostTransactionEventsUseCase

open class PostTransactionEventsUseCaseImpl(
    private val retrieveUnprocessedTransactionsEventsDataSourceGateway: RetrieveUnprocessedTransactionsEventsDataSourceGateway,
    private val updateTransactionEventDataSourceGateway: UpdateTransactionEventDataSourceGateway,
    private val transactionEventEntityMapper: TransactionEventEntityMapper,
    private val postTransactionEventQueueGateway: PostTransactionEventQueueGateway
) : PostTransactionEventsUseCase {

    @Loggable
    override fun execute() {
        val transactionEvents = retrieveUnprocessedTransactionsEventsDataSourceGateway.execute()

        transactionEvents.forEach { transactionEvent ->
            val transactionEventEntity = transactionEventEntityMapper.toEntity(transactionEvent)
            transactionEventEntity.setToProcessed()
            val transactionEventDto = transactionEventEntityMapper.toDto(transactionEventEntity)
            updateTransactionEventDataSourceGateway.execute(transactionEventDto)
            postTransactionEventQueueGateway.execute(transactionEventDto)
        }
    }
}