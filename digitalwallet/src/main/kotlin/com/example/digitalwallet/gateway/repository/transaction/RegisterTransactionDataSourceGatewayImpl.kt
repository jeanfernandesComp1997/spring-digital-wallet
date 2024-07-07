package com.example.digitalwallet.gateway.repository.transaction

import com.example.digitalwallet.common.mapper.TransactionMapper
import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.gateway.RegisterTransactionDataSourceGateway
import org.springframework.stereotype.Component

@Component
class RegisterTransactionDataSourceGatewayImpl(
    private val transactionRepository: TransactionRepository,
    private val transactionMapper: TransactionMapper
) : RegisterTransactionDataSourceGateway {

    @Loggable
    override fun execute(transaction: TransactionDto) {
        transactionRepository.save(transactionMapper.toModel(transaction))
    }
}