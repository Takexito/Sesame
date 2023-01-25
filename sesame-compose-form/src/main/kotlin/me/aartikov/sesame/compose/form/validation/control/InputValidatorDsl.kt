package me.aartikov.sesame.compose.form.validation.control

import me.aartikov.sesame.compose.form.control.InputControl

class InputValidatorBuilder(
    private val inputControl: InputControl,
    private val required: Boolean
) {

    private val validations = mutableListOf<(String) -> ValidationResult>()

    /**
     * Adds an arbitrary validation. Validations are processed sequentially until first error.
     */
    fun validation(validation: (String) -> ValidationResult) {
        validations.add(validation)
    }

    fun build(): InputValidator {
        return InputValidator(inputControl, required, validations)
    }
}

/**
 * Adds an arbitrary validation. Validations are processed sequentially until first error.
 */
fun InputValidatorBuilder.validation(isValid: (String) -> Boolean, errorMessage: String) {
    validation {
        if (isValid(it)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage)
        }
    }
}

/**
 * Adds an arbitrary validation. Validations are processed sequentially until first error.
 */
fun InputValidatorBuilder.validation(
    isValid: (String) -> Boolean,
    errorMessage: () -> String
) {
    validation {
        if (isValid(it)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage.invoke())
        }
    }
}

/**
 * Adds a validation that checks that an input is not blank.
 */
fun InputValidatorBuilder.isNotBlank(errorMessage: String) {
    validation(
        isValid = { it.isNotBlank() },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input matches [regex].
 */
fun InputValidatorBuilder.regex(regex: Regex, errorMessage: String) {
    validation(
        isValid = { regex.matches(it) },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input has at least given number of symbols.
 */
fun InputValidatorBuilder.minLength(length: Int, errorMessage: String) {
    validation(
        isValid = { it.length >= length },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input equals to an input of another input control.
 */
fun InputValidatorBuilder.equalsTo(inputControl: InputControl, errorMessage: String) {
    validation(
        isValid = { it == inputControl.value.value },
        errorMessage
    )
}