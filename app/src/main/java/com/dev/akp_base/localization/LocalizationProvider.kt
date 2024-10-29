package com.dev.akp_base.localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.dev.akp_base.localization.model.LocalizationModel

val LocalLocalization = compositionLocalOf { LocalizationModel() }

object Vocabulary {
    val localization : LocalizationModel
    @Composable
    @ReadOnlyComposable
    get() = LocalLocalization.current
}