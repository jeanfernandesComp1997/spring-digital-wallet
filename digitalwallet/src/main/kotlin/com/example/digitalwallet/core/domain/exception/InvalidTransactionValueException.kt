package com.example.digitalwallet.core.domain.exception

class InvalidTransactionValueException : RuntimeException("Value of transaction must be greater than 0.")