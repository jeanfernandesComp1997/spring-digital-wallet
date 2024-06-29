package com.example.digitalwallet.core.domain.entity

import com.example.digitalwallet.core.domain.enums.UserType
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import java.util.UUID

class User {

    var id: UUID
        private set
    var firstName: String
        private set
    var lastName: String
        private set
    var email: Email
        private set
    var type: UserType
        private set
    var document: Document
        private set
    var password: String
        private set

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        type: String,
        document: String,
        password: String
    ) {
        id = UUID.randomUUID()
        this.firstName = firstName
        this.lastName = lastName
        this.email = Email(email)
        this.type = UserType.valueOf(type)
        this.document = Document(document)
        this.password = password
    }

    constructor(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        type: String,
        document: String,
        password: String
    ) {
        this.id = UUID.fromString(id)
        this.firstName = firstName
        this.lastName = lastName
        this.email = Email(email)
        this.type = UserType.valueOf(type)
        this.document = Document(document)
        this.password = password
    }
}