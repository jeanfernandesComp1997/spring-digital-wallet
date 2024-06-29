package com.example.digitalwallet.gateway.repository.user

import com.example.digitalwallet.common.mapper.UserMapper
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.exception.UserNotFoundException
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFindByIdDataSourceGatewayImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserFindByIdDataSourceGateway {

    override fun execute(id: UUID): UserDto {
        val userModel = userRepository.findById(id)
        return if (userModel.isPresent) {
            userMapper.toDto(userModel.get())
        } else {
            throw UserNotFoundException()
        }
    }

    override fun execute(payerId: UUID, payeeId: UUID): Pair<UserDto, UserDto> {
        val payerModel = userRepository.findById(payerId)
        val payeeModel = userRepository.findById(payeeId)

        return if (payerModel.isPresent && payeeModel.isPresent) {
            Pair(first = userMapper.toDto(payerModel.get()), second = userMapper.toDto(payeeModel.get()))
        } else {
            throw UserNotFoundException()
        }
    }
}