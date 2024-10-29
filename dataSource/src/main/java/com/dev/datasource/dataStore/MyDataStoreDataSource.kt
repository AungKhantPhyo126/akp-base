package com.dev.datasource.dataStore

import com.dev.datasource.model.response.LocalizationResponse
import kotlinx.coroutines.flow.Flow
import java.util.Locale

interface MyDataStoreDataSource {
    val localeFlow:Flow<Locale>
    fun getAccessToken(): String
    suspend fun saveAccessToken(token:String)
    suspend fun changeLocale(locale: Locale)
    suspend fun getLocalization():Map<String,LocalizationResponse>
}