package me.aartikov.sesame.compose.form.options

/**
 * Options to request software keyboard to capitalize the text. Applies to languages which
 * has upper-case and lower-case letters.
 */
@JvmInline
value class KeyboardCapitalization internal constructor(internal val value: Int) {

    override fun toString(): String {
        return when (this) {
            None -> "None"
            Characters -> "Characters"
            Words -> "Words"
            Sentences -> "Sentences"
            else -> "Invalid"
        }
    }

    companion object {
        /**
         * Do not auto-capitalize text.
         */
        val None = KeyboardCapitalization(0)

        /**
         * Capitalize all characters.
         */
        val Characters = KeyboardCapitalization(1)

        /**
         * Capitalize the first character of every word.
         */
        val Words = KeyboardCapitalization(2)

        /**
         * Capitalize the first character of each sentence.
         */
        val Sentences = KeyboardCapitalization(3)
    }
}