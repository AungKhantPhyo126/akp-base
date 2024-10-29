package com.dev.akp_base.localization

import com.dev.akp_base.localization.model.LanguageUiModel
import com.dev.akp_base.localization.model.LocalizationModel
import kotlinx.coroutines.flow.Flow
import java.util.Locale

interface LocalizationRepository {
    val localizationFlow : Flow<LocalizationModel>
    val localeFlow : Flow<Locale>

    val languagesFlow : Flow<List<LanguageUiModel>>

    suspend fun changeLocale(locale: Locale)

    /** to use in non-compose scopes, for compose scopes you can use LocalLocalization **/
    fun  getLocalization() : LocalizationModel?
}