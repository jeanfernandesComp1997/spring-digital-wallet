package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.application.rest.api.transfer.request.TransferRequest
import com.example.digitalwallet.application.rest.api.transfer.response.TransferResponse
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TransferMapper {

    fun toDto(transferRequest: TransferRequest): TransferInputDto

    @Mapping(source = "transactionDto.payerWallet.user.id", target = "payer")
    @Mapping(source = "transactionDto.payeeWallet.user.id", target = "payee")
    fun toResponse(transactionDto: TransactionDto): TransferResponse
}