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
) {

    override fun toString(): String {
        val value = StringBuilder()
        value.append("User (")
        value.append("id: $id,")
        value.append("firstName: $firstName,")
        value.append("lastName: $lastName,")
        value.append("email: *******,")
        value.append("type: $type,")
        value.append("document: *******,")
        value.append("password: *******)")

        return value.toString()
    }
}