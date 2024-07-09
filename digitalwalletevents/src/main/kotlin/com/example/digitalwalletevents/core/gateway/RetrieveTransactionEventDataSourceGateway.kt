package com.example.digitalwalletevents.core.gateway

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import java.util.UUID

interface RetrieveTransactionEventDataSourceGateway {

    fun execute(id: UUID): TransactionEventDto
}