package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.dto.WalletDto
import com.example.digitalwallet.core.domain.entity.User
import com.example.digitalwallet.core.domain.entity.Wallet
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import com.example.digitalwallet.gateway.repository.wallet.model.WalletModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface WalletMapper {



    @Mapping(source = "walletModel.user.email", target = "user.email", qualifiedByName = ["stringEmailToValueObject"])
    @Mapping(
        source = "walletModel.user.document",
        target = "user.document",
        qualifiedByName = ["stringDocumentToValueObject"]
    )
    fun toDto(walletModel: WalletModel): WalletDto

    @Mapping(source = "walletDto.user.email", target = "user.email", qualifiedByName = ["emailValueObjectToString"])
    @Mapping(
        source = "walletDto.user.document",
        target = "user.document",
        qualifiedByName = ["documentValueObjectToString"]
    )
    fun toModel(walletDto: WalletDto): WalletModel

    @Named("stringEmailToValueObject")
    fun stringEmailToValueObject(address: String): Email {
        return Email(address)
    }

    @Named("stringDocumentToValueObject")
    fun stringDocumentToValueObject(value: String): Document {
        return Document(value)
    }

    @Named("emailValueObjectToString")
    fun emailValueObjectToString(address: Email): String {
        return address.address
    }

    @Named("documentValueObjectToString")
    fun documentValueObjectToString(value: Document): String {
        return value.value
    }
}