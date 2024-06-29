package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionAuthorizationDto
import com.example.digitalwallet.gateway.http.authorizerservice.response.TransactionAuthorizationResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TransactionAuthorizationMapper {

    @Mapping(source = "authorizationResponse.data.authorization", target = "isAuthorized")
    fun toDto(authorizationResponse: TransactionAuthorizationResponse): TransactionAuthorizationDto
}