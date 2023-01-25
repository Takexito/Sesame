package me.aartikov.sesame.compose.form.validation.form

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import me.aartikov.sesame.compose.form.control.ValidatableControl
import me.aartikov.sesame.compose.form.control.computed

/**
 * Validates a form dynamically and emits validation result. Validation called whenever a value or skipInValidation
 * of some control is changed.
 */
fun CoroutineScope.dynamicValidationResult(formValidator: FormValidator): StateFlow<FormValidationResult> {
    val result = MutableStateFlow(formValidator.validate(displayResult = false))
    formValidator.validators.forEach { (control, _) ->
        callWhenControlEdited(this, control) {
            result.value = formValidator.validate(displayResult = false)
        }
    }
    return result
}

//TODO: Check
private fun callWhenControlEdited(
    coroutineScope: CoroutineScope,
    control: ValidatableControl<*>,
    callback: () -> Unit
) {
    computed(control.value, control.skipInValidation) { v1, v2 -> v1 to v2 }
        .drop(1)
        .onEach { callback() }
        .launchIn(coroutineScope)
}