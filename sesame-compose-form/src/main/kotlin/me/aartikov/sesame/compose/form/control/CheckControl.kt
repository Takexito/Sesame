package me.aartikov.sesame.compose.form.control

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.aartikov.sesame.compose.form.util.computed

/**
 * Logical representation of a control with checkable state (CheckBox, Switch, etc). It allows to manage checked state from ViewModel.
 */
class CheckControl(
    coroutineScope: CoroutineScope,
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
    override val error: MutableStateFlow<StringDesc?> = MutableStateFlow(null)

    override val value = checked

    override val skipInValidation =
        computed(coroutineScope, visible, enabled) { visible, enabled -> !visible || !enabled }

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
