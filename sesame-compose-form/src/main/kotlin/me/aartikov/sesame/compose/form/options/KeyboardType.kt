package me.aartikov.sesame.compose.form.options

/**
 * Values representing the different available Keyboard Types.
 */
@JvmInline
value class KeyboardType internal constructor(@Suppress("unused") private val value: Int) {

    override fun toString(): String {
        return when (this) {
            Text -> "Text"
            Ascii -> "Ascii"
            Number -> "Number"
            Phone -> "Phone"
            Uri -> "Uri"
            Email -> "Email"
            Password -> "Password"
            NumberPassword -> "NumberPassword"
            else -> "Invalid"
        }
    }

    companion object {
        /**
         * A keyboard type used to request an IME that shows regular keyboard.
         */
        val Text: KeyboardType = KeyboardType(1)

        /**
         * A keyboard type used to request an IME that is capable of inputting ASCII characters.
         */
        val Ascii: KeyboardType = KeyboardType(2)

        /**
         * A keyboard type used to request an that is capable of inputting digits.
         */
        val Number: KeyboardType = KeyboardType(3)

        /**
         * A keyboard type used to request an IME that is capable of inputting phone numbers.
         */
        val Phone: KeyboardType = KeyboardType(4)

        /**
         * A keyboard type used to request an IME that is capable of inputting URIs.
         */
        val Uri: KeyboardType = KeyboardType(5)

        /**
         * A keyboard type used to request an IME that is capable of inputting email addresses.
         */
        val Email: KeyboardType = KeyboardType(6)

        /**
         * A keyboard type used to request an IME that is capable of inputting password
         */
        val Password: KeyboardType = KeyboardType(7)

        /**
         * A keyboard type used to request an IME that is capable of inputting number password.
         */
        val NumberPassword: KeyboardType = KeyboardType(8)
    }
}