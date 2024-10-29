package com.dev.akp_base.localization

import com.dev.akp_base.localization.model.ENGLISH
import com.dev.akp_base.localization.model.LocalizationModel
import com.dev.akp_base.localization.model.MYANMAR
import com.dev.datasource.model.response.LocalizationResponse
import java.util.Locale

fun Map<String,LocalizationResponse>.toLocalizationModel(locale:Locale):LocalizationModel{
    return LocalizationModel().also { model ->
        model.name = this["name"].toLanguage(locale)
    }
}

private fun LocalizationResponse?.toLanguage(locale:Locale):String{
    return if (this != null){
        when(locale){
            ENGLISH -> english.replace("{$}","%s")
            MYANMAR -> myanmar.replace("{$}","%s")
            else -> english.replace("{$}","%s")
        }
    }else ""
}