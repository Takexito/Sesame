package me.aartikov.sesame.compose.form.options

@JvmInline
value class ImeAction internal constructor(@Suppress("unused") private val value: Int) {

    override fun toString(): String {
        return when (this) {
            None -> "None"
            Default -> "Default"
            Go -> "Go"
            Search -> "Search"
            Send -> "Send"
            Previous -> "Previous"
            Next -> "Next"
            Done -> "Done"
            else -> "Invalid"
        }
    }

    companion object {
        /**
         * Use the platform and keyboard defaults and let the keyboard to decide the action. The
         * keyboards will mostly show one of [Done] or [None] actions based on the single/multi
         * line configuration.
         */
        val Default: ImeAction = ImeAction(1)

        /**
         * Represents that no action is expected from the keyboard.
         */
        val None: ImeAction = ImeAction(0)

        /**
         * Represents that the user would like to go to the target of the text in the input i.e.
         * visiting a URL.
         */
        val Go: ImeAction = ImeAction(2)

        /**
         * Represents that the user wants to execute a search, i.e. web search query.
         */
        val Search: ImeAction = ImeAction(3)

        /**
         * Represents that the user wants to send the text in the input, i.e. an SMS.
         */
        val Send: ImeAction = ImeAction(4)

        /**
         * Represents that the user wants to return to the previous input i.e. going back to the
         * previous field in a form.
         */
        val Previous: ImeAction = ImeAction(5)

        /**
         * Represents that the user is done with the current input, and wants to move to the next
         * one i.e. moving to the next field in a form.
         */
        val Next: ImeAction = ImeAction(6)

        /**
         * Represents that the user is done providing input to a group of inputs. Some
         * kind of finalization behavior should now take place i.e. the field was the last element in
         * a group and the data input is finalized.
         */
        val Done: ImeAction = ImeAction(7)
    }
}