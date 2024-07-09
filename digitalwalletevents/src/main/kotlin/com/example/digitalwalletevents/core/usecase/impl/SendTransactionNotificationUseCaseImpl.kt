package com.example.digitalwalletevents.core.usecase.impl

import com.example.digitalwalletevents.common.mapper.TransactionEventEntityMapper
import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.gateway.EmailNotificationGateway
import com.example.digitalwalletevents.core.gateway.RetrieveTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.gateway.UpdateTransactionEventDataSourceGateway
import com.example.digitalwalletevents.core.usecase.SendTransactionNotificationUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class SendTransactionNotificationUseCaseImpl(
    private val updateTransactionEventDataSourceGateway: UpdateTransactionEventDataSourceGateway,
    private val emailNotificationGateway: EmailNotificationGateway,
    private val retrieveTransactionEventDataSourceGateway: RetrieveTransactionEventDataSourceGateway,
    private val transactionEventEntityMapper: TransactionEventEntityMapper
) : SendTransactionNotificationUseCase {

    private val logger: Logger = LoggerFactory.getLogger(SendTransactionNotificationUseCaseImpl::class.java)

    @Loggable
    override fun execute(transactionEvent: TransactionEventDto) {
        val transactionEventEntity = transactionEventEntityMapper.toEntity(
            retrieveTransactionEventDataSourceGateway.execute(transactionEvent.id)
        )

        if (transactionEventEntity.processed) {
            logger.warn("Transaction: {} already processed!", transactionEventEntity.id)
            return
        }

        transactionEventEntity.setToProcessed()
        val notificationMessage = transactionEventEntity.retrieveNotificationMessage()
        val transactionEventDto = transactionEventEntityMapper.toDto(transactionEventEntity)

        emailNotificationGateway.postNotification(transactionEventDto, notificationMessage)
        updateTransactionEventDataSourceGateway.execute(transactionEventDto)
    }
}