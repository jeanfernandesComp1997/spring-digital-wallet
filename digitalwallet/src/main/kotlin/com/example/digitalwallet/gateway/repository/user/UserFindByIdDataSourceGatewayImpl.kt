package com.example.digitalwallet.gateway.repository.user

import com.example.digitalwallet.common.mapper.UserMapper
import com.example.digitalwallet.core.domain.annotations.Loggable
import com.example.digitalwallet.core.domain.dto.UserDto
import com.example.digitalwallet.core.domain.exception.UserNotFoundException
import com.example.digitalwallet.core.gateway.UserFindByIdDataSourceGateway
import java.util.UUID
import java.util.concurrent.CompletableFuture
import org.springframework.stereotype.Component

@Component
class UserFindByIdDataSourceGatewayImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserFindByIdDataSourceGateway {

    @Loggable
    override fun execute(id: UUID): UserDto {
        val userModel = userRepository.findById(id)
        return if (userModel.isPresent) {
            userMapper.toDto(userModel.get())
        } else {
            throw UserNotFoundException()
        }
    }

    @Loggable
    override fun execute(payerId: UUID, payeeId: UUID): Pair<UserDto, UserDto> {
        val payerModelFutureTask = CompletableFuture.supplyAsync {
            userRepository.findById(payerId)
        }
        val payeeModelFutureTask = CompletableFuture.supplyAsync {
            userRepository.findById(payeeId)
        }

        val payerModel = payerModelFutureTask.get()
        val payeeModel = payeeModelFutureTask.get()

        return if (payerModel.isPresent && payeeModel.isPresent) {
            Pair(first = userMapper.toDto(payerModel.get()), second = userMapper.toDto(payeeModel.get()))
        } else {
            throw UserNotFoundException()
        }
    }
}