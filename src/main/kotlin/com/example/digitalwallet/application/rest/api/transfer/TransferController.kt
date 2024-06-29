package com.example.digitalwallet.application.rest.api.transfer

import com.example.digitalwallet.application.rest.api.transfer.request.TransferRequest
import com.example.digitalwallet.application.rest.api.transfer.response.TransferResponse
import com.example.digitalwallet.common.mapper.TransferMapper
import com.example.digitalwallet.core.usecase.TransferFundsUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/transfers")
class TransferController(
    private val transferFundsUseCase: TransferFundsUseCase,
    private val transferMapper: TransferMapper
) {

    @PostMapping
    fun transfer(@RequestBody transferRequest: TransferRequest): ResponseEntity<TransferResponse> {
        val transferResponse = transferFundsUseCase
            .execute(transferMapper.toDto(transferRequest))
            .let { transactionDto ->
                transferMapper.toResponse(transactionDto)
            }

        return ResponseEntity.ok(transferResponse)
    }
}