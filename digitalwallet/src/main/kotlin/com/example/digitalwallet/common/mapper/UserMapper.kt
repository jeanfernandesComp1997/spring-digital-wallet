package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import com.example.digitalwallet.gateway.repository.user.model.UserModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "userModel.email", target = "email", qualifiedByName = ["stringEmailToValueObject"])
    @Mapping(source = "userModel.document", target = "document", qualifiedByName = ["stringDocumentToValueObject"])
    fun toDto(userModel: UserModel): UserDto

    @Named("stringEmailToValueObject")
    fun stringEmailToValueObject(address: String): Email {
        return Email(address)
    }

    @Named("stringDocumentToValueObject")
    fun stringDocumentToValueObject(value: String): Document {
        return Document(value)
    }
}