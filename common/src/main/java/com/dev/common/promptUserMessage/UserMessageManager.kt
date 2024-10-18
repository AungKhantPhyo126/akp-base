package com.dev.common.promptUserMessage

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserMessageManager {
    private val _messages = MutableStateFlow<UserPrompt?>(null)
    val messages: StateFlow<UserPrompt?> get() = _messages.asStateFlow()

    fun showMessage(userPrompt: UserPrompt) {
        if (userPrompt.message.isNotBlank() && userPrompt.message != "Job was cancelled") {
            _messages.value = userPrompt
        }
    }

    fun showMessage(message: String) {
        if (message.isNotBlank() && message != "Job was cancelled") {
            _messages.value = UserPrompt(
                message = message,
                displayType = DisplayType.DIALOG
            )
        }
    }

    fun userMessageShown() {
        _messages.value = null
    }
}

/* Usage:
*  UserMessageManager.showMessage("Your message here")
* */