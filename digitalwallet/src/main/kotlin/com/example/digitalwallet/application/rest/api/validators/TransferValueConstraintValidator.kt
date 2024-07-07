package com.example.digitalwallet.application.rest.api.validators

import com.example.digitalwallet.application.rest.api.validators.annotations.ValidTransferValue
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.math.BigDecimal

class TransferValueConstraintValidator : ConstraintValidator<ValidTransferValue, BigDecimal> {

    override fun isValid(value: BigDecimal, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        return value > BigDecimal(0)
    }
}