package me.aartikov.sesame.compose.form.control


fun interface VisualTransformation {
    /**
     * Change the visual output of given text.
     *
     * Note that the returned text length can be different length from the given text. The composable
     * will call the offset translator for converting offsets for various reasons, cursor drawing
     * position, text selection by gesture, etc.
     *
     * @param text The original text
     * @return filtered text.
     */
    fun filter(text: String): String

    companion object {
        /**
         * A special visual transformation object indicating that no transformation is applied.
         */
        val None: VisualTransformation = VisualTransformation { text -> text }
    }
}

/**
 * The Visual Filter can be used for password Input Field.
 *
 * Note that this visual filter only works for ASCII characters.
 *
 * @param mask The mask character used instead of original text.
 */
class PasswordVisualTransformation(val mask: Char = '\u2022') :
    VisualTransformation {
    override fun filter(text: String): String {
        return mask.toString().repeat(text.length)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PasswordVisualTransformation) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}