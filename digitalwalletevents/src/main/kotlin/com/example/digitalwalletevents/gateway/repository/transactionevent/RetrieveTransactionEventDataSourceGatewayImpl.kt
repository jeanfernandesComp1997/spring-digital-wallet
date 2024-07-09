package com.example.digitalwalletevents.gateway.repository.transactionevent

import com.example.digitalwalletevents.common.mapper.TransactionEventMapper
import com.example.digitalwalletevents.core.domain.annotations.Loggable
import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.core.domain.exception.TransactionEventNotFoundException
import com.example.digitalwalletevents.core.gateway.RetrieveTransactionEventDataSourceGateway
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class RetrieveTransactionEventDataSourceGatewayImpl(
    private val transactionEventRepository: TransactionEventRepository,
    private val transactionEventMapper: TransactionEventMapper
) : RetrieveTransactionEventDataSourceGateway {

    @Loggable
    override fun execute(id: UUID): TransactionEventDto {
        val transactionEventModel = transactionEventRepository.findById(id)
        return if (transactionEventModel.isPresent) {
            transactionEventMapper.toDto(transactionEventModel.get())
        } else {
            throw TransactionEventNotFoundException()
        }
    }
}