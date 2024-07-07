package com.example.digitalwallet.core.usecase

import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.dto.TransferInputDto

interface TransferFundsUseCase {

    fun execute(transferInput: TransferInputDto): TransactionDto
}