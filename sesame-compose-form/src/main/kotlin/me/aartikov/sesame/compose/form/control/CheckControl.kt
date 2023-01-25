package me.aartikov.sesame.compose.form.control

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Logical representation of a control with checkable state (CheckBox, Switch, etc). It allows to manage checked state from ViewModel.
 */
class CheckControl(
    initialChecked: Boolean = false
) : ValidatableControl<Boolean> {

    /**
     * Is control checked.
     */
    val checked: MutableStateFlow<Boolean> = MutableStateFlow(initialChecked)

    /**
     * Is control visible.
     */
    val visible: MutableStateFlow<Boolean> = MutableStateFlow(true)

    /**
     * Is control enabled.
     */
    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(true)

    /**
     * Displayed error.
     */
    override val error: MutableStateFlow<String?> = MutableStateFlow(null)

    override val value = checked

    override val skipInValidation =
        computed(visible, enabled) { visible, enabled -> !visible || !enabled }

    private val mutableScrollToItEventFlow = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val scrollToItEvent get() = mutableScrollToItEventFlow.asSharedFlow()

    override fun requestFocus() {
        mutableScrollToItEventFlow.tryEmit(Unit)
    }

    /**
     * Called automatically when checked is changed on a view side.
     */
    fun onCheckedChanged(checked: Boolean) {
        this.checked.value = checked
    }
}
