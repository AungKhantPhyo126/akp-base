package com.dev.datasource.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dev.datasource.roomDatabase.MyRoomDataSource
import com.dev.datasource.roomDatabase.dao.UserInfoDao
import com.dev.datasource.roomDatabase.di.Dispatcher
import com.dev.datasource.roomDatabase.di.MyDispatchers
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MyDatStoreDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(MyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val preferences: DataStore<Preferences>,
    private val userInfoDao: UserInfoDao
) : MyDataStoreDataSource {

    companion object {
        const val KEY_ACCESS_TOKEN = "access-token"
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
        saveString(KEY_ACCESS_TOKEN,token)
    }


}