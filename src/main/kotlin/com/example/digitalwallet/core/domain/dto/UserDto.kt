package com.example.digitalwallet.core.domain.dto

import java.util.UUID

data class UserDto(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val type: String,
    val document: String,
    val password: String
)