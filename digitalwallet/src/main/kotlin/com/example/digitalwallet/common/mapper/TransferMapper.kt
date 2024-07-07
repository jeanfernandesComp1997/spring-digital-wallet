package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.application.rest.api.transfer.request.TransferRequest
import com.example.digitalwallet.application.rest.api.transfer.response.TransferResponse
import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TransferMapper {

    @Mapping(source = "transferRequest.payer", target = "payerId")
    @Mapping(source = "transferRequest.payee", target = "payeeId")
    fun toDto(transferRequest: TransferRequest): TransferInputDto

    @Mapping(source = "transaction.payerWallet.user.id", target = "payer")
    @Mapping(source = "transaction.payeeWallet.user.id", target = "payee")
    fun toResponse(transaction: TransactionDto): TransferResponse
}