package com.example.digitalwallet.core.domain.valueobjects

data class Email(val address: String) {

    companion object {
        private const val EMAIL_STRING_VALIDATOR = "@"
    }

    init {
        require(address.isNotBlank()) { InvalidEmailException("Email address cannot be blank") }
        require(address.contains(EMAIL_STRING_VALIDATOR)) { InvalidEmailException("Invalid email address format") }
    }

    class InvalidEmailException(message: String) : IllegalArgumentException(message)
}