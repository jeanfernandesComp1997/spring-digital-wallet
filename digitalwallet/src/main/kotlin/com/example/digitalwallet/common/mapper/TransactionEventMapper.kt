package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionEventDto
import com.example.digitalwallet.core.domain.entity.Transaction
import com.example.digitalwallet.core.domain.entity.Wallet
import com.example.digitalwallet.core.domain.enums.TransactionEventType
import com.example.digitalwallet.gateway.repository.transactionevent.model.TransactionEventModel
import java.util.UUID
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TransactionEventMapper {

    fun toModel(transactionEvent: TransactionEventDto): TransactionEventModel

    @Mapping(source = "id", target = "id")
    @Mapping(source = "payerWallet.user.firstName", target = "payerFirstName")
    @Mapping(source = "payerWallet.user.email.address", target = "payerEmail")
    @Mapping(source = "payeeWallet.user.firstName", target = "payeeFirstName")
    @Mapping(source = "payeeWallet.user.email.address", target = "payeeEmail")
    @Mapping(source = "transaction.amount", target = "amount")
    @Mapping(source = "transaction.date", target = "date")
    @Mapping(source = "transactionEventType", target = "transactionEventType")
    @Mapping(target = "sent", constant = "false")
    @Mapping(target = "processed", constant = "false")
    fun toDto(
        id: UUID,
        payerWallet: Wallet,
        payeeWallet: Wallet,
        transaction: Transaction,
        transactionEventType: TransactionEventType
    ): TransactionEventDto
}