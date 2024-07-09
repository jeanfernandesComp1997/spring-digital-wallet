package com.example.digitalwalletevents.common.mapper

import com.example.digitalwalletevents.core.domain.dto.TransactionEventDto
import com.example.digitalwalletevents.gateway.repository.transactionevent.model.TransactionEventModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TransactionEventMapper {

    fun toDto(transactionEventModel: TransactionEventModel): TransactionEventDto

    fun toModel(transactionEventDto: TransactionEventDto): TransactionEventModel
}