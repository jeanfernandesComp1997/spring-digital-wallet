package com.example.digitalwallet.gateway.repository.transactionevent

import com.example.digitalwallet.common.mapper.TransactionEventMapper
import com.example.digitalwallet.core.domain.dto.TransactionEventDto
import com.example.digitalwallet.core.gateway.RegisterTransactionEventDataSourceGateway
import org.springframework.stereotype.Component

@Component
class RegisterTransactionEventDataSourceGatewayImpl(
    private val transactionRepository: TransactionEventRepository,
    private val transactionEventMapper: TransactionEventMapper
) : RegisterTransactionEventDataSourceGateway {

    override fun execute(transactionEventDto: TransactionEventDto) {
        transactionRepository.save(transactionEventMapper.toModel(transactionEventDto))
    }
}