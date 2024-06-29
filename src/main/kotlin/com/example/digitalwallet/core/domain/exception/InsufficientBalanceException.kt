package com.example.digitalwallet.core.domain.exception

class InsufficientBalanceException : RuntimeException(message = "Insufficient balance to finish the transaction.")