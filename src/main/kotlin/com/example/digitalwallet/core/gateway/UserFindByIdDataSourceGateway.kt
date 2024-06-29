package com.example.digitalwallet.core.gateway

import com.example.digitalwallet.core.domain.dto.UserDto
import java.util.UUID

interface UserFindByIdDataSourceGateway {

    fun execute(id: UUID): UserDto
    fun execute(payerId: UUID, payeeId: UUID): Pair<UserDto, UserDto>
}