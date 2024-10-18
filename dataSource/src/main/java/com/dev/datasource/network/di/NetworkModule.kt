package com.dev.datasource.network.di

import android.content.Context
import com.dev.datasource.BuildConfig
import com.dev.datasource.network.utils.MyAuthenticator
import com.dev.datasource.network.utils.createOkHttpClient
import com.dev.datasource.network.utils.createRetrofitClient
import com.dev.datasource.roomDatabase.MyRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        @Singleton
        @Provides
        fun providesCoroutineScope(): CoroutineScope {
            return CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }

        @Provides
        @Singleton
        @Named("akpOkHttpClient")
        fun provideOkHttpClient(
            @ApplicationContext
            context: Context,
            localDataSource: MyRoomDataSource,
            authenticator: MyAuthenticator
        ): OkHttpClient {
            return createOkHttpClient(
                context,
                HttpLoggingInterceptor(),
                localDataSource,
                authenticator
            )
        }

        @Provides
        @Singleton
        @Named("akp_base")
        fun provideRetrofit(
            @Named("akpOkHttpClient") okHttpClient: OkHttpClient,
            networkJson: Json
        ): Retrofit {
            return createRetrofitClient(
                BuildConfig.BASE_URL,
                okHttpClient,
                networkJson
            )
        }
    }
}