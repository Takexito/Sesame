@file:Suppress("UNCHECKED_CAST")

package me.aartikov.sesamecomposesample.features.form.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

internal fun <T, R> computed(
    coroutineScope: CoroutineScope,
    flow: StateFlow<T>,
    transform: (T) -> R
): StateFlow<R> {
    val initialValue = flow.value
    val resultFlow = MutableStateFlow(transform(initialValue))
    coroutineScope.launch {
        flow.dropWhile {
            it == initialValue
        }
            .collect {
                resultFlow.value = transform(it)
            }
    }
    return resultFlow
}