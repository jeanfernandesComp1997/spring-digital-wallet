package com.example.digitalwallet.gateway.repository.user

import com.example.digitalwallet.gateway.repository.user.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserModel, UUID>