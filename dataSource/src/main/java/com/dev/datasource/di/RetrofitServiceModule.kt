package com.dev.datasource.di

import com.dev.datasource.network.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(@Named("akp_base") retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}