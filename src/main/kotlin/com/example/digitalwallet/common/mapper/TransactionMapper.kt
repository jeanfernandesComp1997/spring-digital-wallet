package com.example.digitalwallet.common.mapper

import com.example.digitalwallet.core.domain.dto.TransactionDto
import com.example.digitalwallet.core.domain.entity.Transaction

interface TransactionMapper {

    fun toDto(transaction: Transaction): TransactionDto
}