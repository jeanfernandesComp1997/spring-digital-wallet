package com.example.digitalwallet.gateway.repository.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "`user`")
@Table(name = "`user`")
data class UserModel(
    @Id
    val id: UUID,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    val email: String,
    val type: String,
    val document: String,
    val password: String
)
