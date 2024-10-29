package com.dev.datasource.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dev.datasource.model.response.LocalizationResponse
import com.dev.datasource.roomDatabase.dao.UserInfoDao
import com.dev.datasource.roomDatabase.di.Dispatcher
import com.dev.datasource.roomDatabase.di.MyDispatchers
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MyDatStoreDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(MyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val preferences: DataStore<Preferences>,
    private val userInfoDao: UserInfoDao
) : MyDataStoreDataSource {

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    override val localeFlow: Flow<Locale>
        get() = preferences.data
            .map { it[stringPreferencesKey(KEY_LOCALE)] }
            .map { Locale(it ?: "en") }

    companion object {
        const val KEY_ACCESS_TOKEN = "access-token"
        const val KEY_LOCALE = "key-locale"
    }

    private suspend fun saveString(key: String, value: String) {
        stringPreferencesKey(key).let { k ->
            preferences.edit {
                it[k] = value
            }
        }
    }

    private fun getString(key: String): String {
        return runBlocking(ioDispatcher) {
            preferences.data
                .map { it[stringPreferencesKey(key)] }
                .first()
                .orEmpty()
        }
    }

    private suspend fun saveBoolean(key: String, value: Boolean) {
        booleanPreferencesKey(key).let { k ->
            preferences.edit {
                it[k] = value
            }
        }
    }

    override fun getAccessToken() = getString(KEY_ACCESS_TOKEN)

    override suspend fun saveAccessToken(token: String) {
        saveString(KEY_ACCESS_TOKEN, token)
    }

    override suspend fun changeLocale(locale: Locale) {
        saveString(KEY_LOCALE, locale.language)
    }

    override suspend fun getLocalization(): Map<String, LocalizationResponse> {
        val type = Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            LocalizationResponse::class.java
        )
        val adapter = moshi.adapter<Map<String, LocalizationResponse>>(type)
        lateinit var jsonString: String
        lateinit var bufferedReader: BufferedReader
        try {
            bufferedReader = context
                .assets
                .open("localization.json")
                .bufferedReader()

            jsonString = bufferedReader.use { it.readText() }
            withContext(Dispatchers.IO) { bufferedReader.close()}
        }catch (ioException:IOException){
            ioException.printStackTrace()
        }
        return adapter.fromJson(jsonString)?: mapOf(
            "en" to LocalizationResponse(
                english = "english",
                myanmar = "myanmar"
            )
        )
    }


}