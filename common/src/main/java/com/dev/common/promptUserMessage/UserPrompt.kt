package com.dev.common.promptUserMessage

data class UserPrompt (
    val message: String,
    val displayType: DisplayType = DisplayType.DIALOG,
    val title: String = ""
)

enum class DisplayType {
    DIALOG,
    SWEET_SUCCESS,
    FAILURE,
    SNAP
}