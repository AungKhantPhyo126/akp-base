package com.dev.akp_base.localization.model

import java.util.Locale

data class LanguageUiModel(
    val id:Int = 0 ,
    val displayName: String,
    val locale: Locale,
    val isSelected: Boolean
)

val supportedLanguagesUiModels = listOf(
    LanguageUiModel(
        displayName = "English",
        locale = ENGLISH,
        isSelected = true
    ),
    LanguageUiModel(
        displayName = "Myanmar",
        locale = MYANMAR,
        isSelected = true
    )
)
