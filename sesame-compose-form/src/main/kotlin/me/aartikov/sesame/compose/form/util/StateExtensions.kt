@file:Suppress("UNCHECKED_CAST")

package me.aartikov.sesame.compose.form.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

fun <T, R> computed(
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

fun <T1, T2, R> computed(
    coroutineScope: CoroutineScope,
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    transform: (T1, T2) -> R
): StateFlow<R> {
    return computedImpls(coroutineScope, flow1, flow2) { args: List<*> ->
        transform(
            args[0] as T1,
            args[1] as T2
        )
    }
}

private inline fun <T, R> computedImpls(
    coroutineScope: CoroutineScope,
    vararg flows: StateFlow<T>,
    crossinline transform: (List<T>) -> R
): StateFlow<R> {
    val initialValues = flows.map { it.value }
    val elementsFlow = MutableStateFlow(initialValues)
    val resultFlow = MutableStateFlow(transform(initialValues))

    flows.forEachIndexed { index, flow ->
        coroutineScope.launch {
            flow
                .dropWhile {
                    it == initialValues[index]
                }
                .collect {
                    elementsFlow.value =
                        elementsFlow.value.toMutableList().apply { this[index] = it }
                }
        }
    }

    coroutineScope.launch {
        elementsFlow
            .dropWhile {
                it == initialValues
            }
            .collect {
                resultFlow.value = transform(it)
            }
    }

    return resultFlow
}