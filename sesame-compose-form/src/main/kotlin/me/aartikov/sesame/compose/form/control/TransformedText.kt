package me.aartikov.sesame.compose.form.control

/**
 * The transformed text with offset offset mapping
 */
class TransformedText(
    /**
     * The transformed text
     */
    val text: String,

    /**
     * The map used for bidirectional offset mapping from original to transformed text.
     */
    val offsetMapping: OffsetMapping
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TransformedText) return false
        if (text != other.text) return false
        if (offsetMapping != other.offsetMapping) return false
        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + offsetMapping.hashCode()
        return result
    }

    override fun toString(): String {
        return "TransformedText(text=$text, offsetMapping=$offsetMapping)"
    }
}