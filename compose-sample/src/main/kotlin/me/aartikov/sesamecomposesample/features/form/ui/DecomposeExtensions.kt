package me.aartikov.sesamecomposesample.features.form.ui

import com.arkivanov.decompose.ComponentContext
import me.aartikov.sesame.compose.form.control.CheckControl
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.compose.form.control.TextTransformation
import me.aartikov.sesame.compose.form.control.VisualTransformation
import me.aartikov.sesame.compose.form.options.KeyboardOptions
import me.aartikov.sesamecomposesample.core.utils.componentCoroutineScope

fun ComponentContext.InputControl(
    initialText: String = "",
    singleLine: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions,
    textTransformation: TextTransformation? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) = InputControl(
    componentCoroutineScope(),
    initialText,
    singleLine,
    maxLength,
    keyboardOptions,
    textTransformation,
    visualTransformation
)

fun ComponentContext.CheckControl(initialChecked: Boolean = false) =
    CheckControl(componentCoroutineScope(), initialChecked)