package me.aartikov.sesame.compose.form.control

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.aartikov.sesame.compose.form.options.KeyboardOptions

/**
 * Logical representation of an input field. It allows to configure an input field and manage its state from ViewModel.
 */
class InputControl(
    initialText: String = "",
    val singleLine: Boolean = true,
    val maxLength: Int = Int.MAX_VALUE,
    val keyboardOptions: KeyboardOptions,
    val textTransformation: TextTransformation? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None
) : ValidatableControl<String> {

    /**
     * Current text.
     */
    val text = MutableStateFlow(correctText(initialText))

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

    override val value = text

    override val skipInValidation =
        computed(visible, enabled) { visible, enabled -> !visible || !enabled }

    private val mutableScrollToItEventFlow = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val scrollToItEvent get() = mutableScrollToItEventFlow.asSharedFlow()

    override fun requestFocus() {
        this.hasFocus.value = true
        mutableScrollToItEventFlow.tryEmit(Unit)
    }

    /**
     * Should be called when text is changed on a view side.
     */
    fun onTextChanged(text: String) {
        this.text.value = text
    }

    fun onFocusChanged(hasFocus: Boolean) {
        this.hasFocus.value = hasFocus
    }

    private fun correctText(text: String): String {
        val transformedText = textTransformation?.transform(text) ?: text
        return transformedText.take(maxLength)
    }
}
