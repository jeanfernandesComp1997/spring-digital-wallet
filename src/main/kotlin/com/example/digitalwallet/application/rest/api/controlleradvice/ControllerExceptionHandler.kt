package com.example.digitalwallet.application.rest.api.controlleradvice

import com.example.digitalwallet.application.rest.api.controlleradvice.response.ErrorResponse
import com.example.digitalwallet.core.domain.exception.ExternalTransactionAuthorizerDeniedException
import com.example.digitalwallet.core.domain.exception.ForbiddenTransactionException
import com.example.digitalwallet.core.domain.exception.InsufficientBalanceException
import com.example.digitalwallet.core.domain.exception.InvalidTransactionValueException
import com.example.digitalwallet.core.domain.exception.InvalidUsersForTransferException
import com.example.digitalwallet.core.domain.exception.UserNotFoundException
import com.example.digitalwallet.core.domain.exception.WalletNotFoundException
import com.example.digitalwallet.core.domain.valueobjects.Document
import com.example.digitalwallet.core.domain.valueobjects.Email
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)

    private fun logError(exception: Exception) {
        logger.info("Handling exception in thread: ${Thread.currentThread().name}")
        logger.error("Error: {}", exception.message)
    }

    // this generic error handling is only for tests, for production never return details of the exception
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Any> {
        logError(exception)
        return ResponseEntity
            .internalServerError()
            .body(
                ErrorResponse(
                    code = HttpStatus.INTERNAL_SERVER_ERROR,
                    message = exception.message ?: ""
                )
            )
    }

    @ExceptionHandler(
        Document.InvalidDocumentException::class,
        Email.InvalidEmailException::class,
        InvalidTransactionValueException::class,
        InvalidUsersForTransferException::class
    )
    fun handleBadRequestExceptions(exception: Exception): ResponseEntity<ErrorResponse> {
        logError(exception)
        return ResponseEntity
            .badRequest()
            .body(
                ErrorResponse(
                    code = HttpStatus.BAD_REQUEST,
                    message = exception.message ?: ""
                )
            )
    }

    @ExceptionHandler(
        ExternalTransactionAuthorizerDeniedException::class,
        ForbiddenTransactionException::class,
        InsufficientBalanceException::class,
    )
    fun handleForbiddenExceptions(exception: Exception): ResponseEntity<ErrorResponse> {
        logError(exception)
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                ErrorResponse(
                    code = HttpStatus.FORBIDDEN,
                    message = exception.message ?: ""
                )
            )
    }

    @ExceptionHandler(
        UserNotFoundException::class,
        WalletNotFoundException::class
    )
    fun handleNotFoundExceptions(exception: Exception): ResponseEntity<ErrorResponse> {
        logError(exception)
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponse(
                    code = HttpStatus.NOT_FOUND,
                    message = exception.message ?: ""
                )
            )
    }
}