package me.aartikov.sesame.compose.form.control

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.aartikov.sesame.compose.form.options.KeyboardOptions
import me.aartikov.sesame.compose.form.util.computed

/**
 * Logical representation of an input field. It allows to configure an input field and manage its state from ViewModel.
 */
class InputControl(
    coroutineScope: CoroutineScope,
    initialText: String = "",
    val singleLine: Boolean = true,
    val maxLength: Int = Int.MAX_VALUE,
    val keyboardOptions: KeyboardOptions,
    val textTransformation: TextTransformation? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None
) : ValidatableControl<String> {

    private val _text = MutableStateFlow(correctText(initialText))

    /**
     * Current text.
     */
    val text: StateFlow<String>
        get() = _text

    /**
     * Is control visible.
     */
    val visible: MutableStateFlow<Boolean> = MutableStateFlow(true)

    /**
     * Is control enabled.
     */
    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(true)

    /**
     * Is control has focus.
     */
    val hasFocus: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * Displayed error.
     */
    override val error: MutableStateFlow<String?> = MutableStateFlow(null)

    override val value: StateFlow<String> = _text

    override val skipInValidation =
        computed(coroutineScope, visible, enabled) { visible, enabled -> !visible || !enabled }

    private val mutableScrollToItEventFlow = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val scrollToItEvent get() = mutableScrollToItEventFlow.asSharedFlow()

    override fun requestFocus() {
        this.hasFocus.value = true
        mutableScrollToItEventFlow.tryEmit(Unit)
    }

    fun setText(text: String) {
        _text.value = correctText(text)
    }

    /**
     * Should be called when text is changed on a view side.
     */
    fun onTextChanged(text: String) {
        _text.value = correctText(text)
    }

    fun onFocusChanged(hasFocus: Boolean) {
        this.hasFocus.value = hasFocus
    }

    private fun correctText(text: String): String {
        val transformedText = textTransformation?.transform(text) ?: text
        return transformedText.take(maxLength)
    }
}
