package com.dev.datasource.network.utils

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dev.datasource.BuildConfig
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.network.interceptor.HttpInterceptor
import com.dev.datasource.network.interceptor.ResponseInterceptor
import com.dev.datasource.network.interceptor.myTokenInterceptor
import com.dev.datasource.roomDatabase.MyRoomDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

fun createRetrofitClient(
    url: String,
    okHttpClient: OkHttpClient,
    networkJson: Json,
): Retrofit = Retrofit.Builder()
    .baseUrl(url)
    .client(okHttpClient)
    .addConverterFactory(NullOnEmptyConverterFactory())
    .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
    .build()


fun createOkHttpClient(
    context: Context,
    httpLoggingInterceptor: HttpLoggingInterceptor,
    localDataSource: MyDataStoreDataSource,
    myAuthenticator: MyAuthenticator
) = OkHttpClient.Builder()
    .authenticator(myAuthenticator)
    .addInterceptor(ResponseInterceptor())
    .addInterceptor { chain ->
        myTokenInterceptor(chain = chain, myDataStoreDataSource = localDataSource)
    }
    .also {
        if (BuildConfig.DEBUG) {
            it.addInterceptor(ChuckerInterceptor.Builder(context).build())
            /** to implement chucker like feature customly for some platform that doesn't support chucker like android tv */
//            it.addInterceptor(HttpInterceptor())
            it.addInterceptor(
                httpLoggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
    }
    .connectTimeout(60L, TimeUnit.SECONDS)
    .readTimeout(60L, TimeUnit.SECONDS)
    .writeTimeout(60L, TimeUnit.SECONDS)
    .build()