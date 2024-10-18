package com.dev.datasource.dataStore

interface MyDataStoreDataSource {
    fun getAccessToken(): String
    suspend fun saveAccessToken(token:String)
}