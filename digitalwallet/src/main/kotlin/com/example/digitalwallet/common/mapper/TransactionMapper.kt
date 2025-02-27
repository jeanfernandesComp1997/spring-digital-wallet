package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import com.example.digitalwallet.gateway.repository.transaction.model.TransactionModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface TransactionMapper {

    @Mapping(
        source = "transaction.payerWallet.user.email",
        target = "payerWallet.user.email",
        qualifiedByName = ["stringEmailToValueObject"]
    )
    @Mapping(
        source = "transaction.payeeWallet.user.email",
        target = "payeeWallet.user.email",
        qualifiedByName = ["stringEmailToValueObject"]
    )
    @Mapping(
        source = "transaction.payerWallet.user.document",
        target = "payerWallet.user.document",
        qualifiedByName = ["documentValueObjectToString"]
    )
    @Mapping(
        source = "transaction.payeeWallet.user.document",
        target = "payeeWallet.user.document",
        qualifiedByName = ["documentValueObjectToString"]
    )
    fun toModel(transaction: TransactionDto): TransactionModel

    @Named("stringEmailToValueObject")
    fun stringEmailToValueObject(address: Email): String {
        return address.address
    }

    @Named("documentValueObjectToString")
    fun documentValueObjectToString(value: Document): String {
        return value.value
    }
}