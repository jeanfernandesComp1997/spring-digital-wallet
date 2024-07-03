package com.example.digitalwallet.core.domain.exception

class InvalidUsersForTransferException : RuntimeException("Users for transfer must be different")