package com.example.digitalwallet.core.domain.dto

import com.example.digitalwallet.core.domain.enums.UserType
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import java.util.UUID

data class UserDto(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: Email,
    val type: UserType,
    val document: Document,
    val password: String
)