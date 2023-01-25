package me.aartikov.sesame.compose.form.options


class KeyboardOptions constructor(
    val capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    val autoCorrect: Boolean = true,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val imeAction: ImeAction = ImeAction.Default
) {
    companion object {
        /**
         * Default [KeyboardOptions]. Please see parameter descriptions for default values.
         */
        val Default = KeyboardOptions()
    }

    /**
     * Returns a new [ImeOptions] with the values that are in this [KeyboardOptions] and provided
     * params.
     *
     * @param singleLine see [ImeOptions.singleLine]
     */
    internal fun toImeOptions(singleLine: Boolean = ImeOptions.Default.singleLine) = ImeOptions(
        singleLine = singleLine,
        capitalization = capitalization,
        autoCorrect = autoCorrect,
        keyboardType = keyboardType,
        imeAction = imeAction
    )

    fun copy(
        capitalization: KeyboardCapitalization = this.capitalization,
        autoCorrect: Boolean = this.autoCorrect,
        keyboardType: KeyboardType = this.keyboardType,
        imeAction: ImeAction = this.imeAction
    ): KeyboardOptions {
        return KeyboardOptions(
            capitalization = capitalization,
            autoCorrect = autoCorrect,
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KeyboardOptions) return false

        if (capitalization != other.capitalization) return false
        if (autoCorrect != other.autoCorrect) return false
        if (keyboardType != other.keyboardType) return false
        if (imeAction != other.imeAction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = capitalization.hashCode()
        result = 31 * result + autoCorrect.hashCode()
        result = 31 * result + keyboardType.hashCode()
        result = 31 * result + imeAction.hashCode()
        return result
    }

    override fun toString(): String {
        return "KeyboardOptions(capitalization=$capitalization, autoCorrect=$autoCorrect, " +
                "keyboardType=$keyboardType, imeAction=$imeAction)"
    }
}