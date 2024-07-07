package com.example.digitalwallet.gateway.repository.transactionmanagement

import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.gateway.TransactionManagementGateway
import com.example.digitalwallet.gateway.repository.transactionmanagement.exception.TransactionExecutionWithoutReturnException
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@Component
class TransactionManagementGatewayImpl(
    transactionManager: PlatformTransactionManager
) : TransactionManagementGateway {

    private val transactionTemplate = TransactionTemplate(transactionManager)

    @Loggable
    override fun <T> doInTransaction(actions: () -> T): T {
        return transactionTemplate.execute<T?> {
            actions()
        } ?: throw TransactionExecutionWithoutReturnException()
    }
}