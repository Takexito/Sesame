package me.aartikov.sesame.compose.form.control

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.sesame.localizedstring.LocalizedString

/**
 * Control that can be validated.
 * @param ValueT type of value managed by a control.
 *
 * @see: [InputControl]
 * @see: [CheckControl]
 */
interface ValidatableControl<ValueT> {

    /**
     * Control value.
     */
    val value: StateFlow<ValueT>

    /**
     * Displayed error.
     */
    val error: MutableStateFlow<LocalizedString?>

    /**
     * Is control should be skipped during validation.
     */
    val skipInValidation: StateFlow<Boolean>

    /**
     * Moves focus to a control.
     */
    fun requestFocus()
}