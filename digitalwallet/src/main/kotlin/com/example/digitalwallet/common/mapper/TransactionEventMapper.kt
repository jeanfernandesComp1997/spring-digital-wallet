package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionEventDto
import com.example.digitalwallet.gateway.repository.transactionevent.model.TransactionEventModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TransactionEventMapper {

    fun toModel(transactionEvent: TransactionEventDto): TransactionEventModel
}