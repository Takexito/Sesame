package me.aartikov.sesamecomposesample.features.form.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction as ComposeImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import me.aartikov.sesame.compose.form.control.VisualTransformation
import me.aartikov.sesame.compose.form.options.ImeAction
import me.aartikov.sesame.compose.form.options.KeyboardCapitalization
import me.aartikov.sesame.compose.form.options.KeyboardOptions
import me.aartikov.sesame.compose.form.options.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions as ComposeKeyboardOptions
import androidx.compose.ui.text.input.KeyboardCapitalization as ComposeKeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType as ComposeKeyBoardType
import androidx.compose.ui.text.input.VisualTransformation as ComposeVisualTransformation

fun VisualTransformation.asCompose(): ComposeVisualTransformation {
    return ComposeVisualTransformation {
        TransformedText(
            AnnotatedString(this.filter(it.text)),
            OffsetMapping.Identity
        )
    }
}

fun KeyboardOptions.asCompose(): ComposeKeyboardOptions {
    KeyboardCapitalization
    return ComposeKeyboardOptions(
        capitalization = capitalization.asCompose(),
        autoCorrect = autoCorrect,
        keyboardType = keyboardType.asCompose(),
        imeAction = imeAction.asCompose()
    )
}

fun KeyboardCapitalization.asCompose(): ComposeKeyboardCapitalization {
    return when (this) {
        KeyboardCapitalization.None -> ComposeKeyboardCapitalization.None
        KeyboardCapitalization.Characters -> ComposeKeyboardCapitalization.Characters
        KeyboardCapitalization.Words -> ComposeKeyboardCapitalization.Words
        KeyboardCapitalization.Sentences -> ComposeKeyboardCapitalization.Sentences
        else -> throw NotImplementedError("Can't find compose equivalent of KeyboardCapitalization - $this")
    }
}

fun KeyboardType.asCompose(): ComposeKeyBoardType {
    return when (this) {
        KeyboardType.Text -> ComposeKeyBoardType.Text
        KeyboardType.Ascii -> ComposeKeyBoardType.Ascii
        KeyboardType.Email -> ComposeKeyBoardType.Email
        KeyboardType.Uri -> ComposeKeyBoardType.Uri
        KeyboardType.Number -> ComposeKeyBoardType.Number
        KeyboardType.NumberPassword -> ComposeKeyBoardType.NumberPassword
        KeyboardType.Password -> ComposeKeyBoardType.Password
        KeyboardType.Phone -> ComposeKeyBoardType.Phone
        else -> throw NotImplementedError("Can't find compose equivalent of KeyboardType - $this")
    }
}

fun ImeAction.asCompose(): ComposeImeAction {
    return when (this) {
        ImeAction.Default -> ComposeImeAction.Default
        ImeAction.None -> ComposeImeAction.None
        ImeAction.Search -> ComposeImeAction.Search
        ImeAction.Go -> ComposeImeAction.Go
        ImeAction.Done -> ComposeImeAction.Done
        ImeAction.Next -> ComposeImeAction.Next
        ImeAction.Send -> ComposeImeAction.Send
        ImeAction.Previous -> ComposeImeAction.Previous

        else -> throw NotImplementedError("Can't find compose equivalent of ImeAction - $this")
    }
}