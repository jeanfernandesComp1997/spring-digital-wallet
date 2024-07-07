package com.example.digitalwallet.application.rest.api.validators.annotations

import com.example.digitalwallet.application.rest.api.validators.TransferValueConstraintValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TransferValueConstraintValidator::class])
@MustBeDocumented
annotation class ValidTransferValue(
    val message: String = "The transfer value must be greater than 0.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
