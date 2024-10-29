package com.dev.datasource.di

import com.dev.datasource.AuthDataSource
import com.dev.datasource.AuthDataSourceImpl
import com.dev.datasource.LocalizationDataSource
import com.dev.datasource.LocalizationDataSourceImpl
import com.dev.datasource.dataStore.MyDatStoreDataSourceImpl
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.roomDatabase.MyRoomDataSource
import com.dev.datasource.roomDatabase.MyRoomDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {


    @Binds
    @Singleton
    abstract fun provideDataStoreDataSource(
        myDatStoreDataSourceImpl: MyDatStoreDataSourceImpl
    ): MyDataStoreDataSource

    @Binds
    @Singleton
    abstract fun provideMyRoomDataSource(
        myRoomDataSourceImpl: MyRoomDataSourceImpl
    ): MyRoomDataSource

    @Binds
    @Singleton
    abstract fun provideAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun provideLocalizationDataSource(
        localizationDataSourceImpl: LocalizationDataSourceImpl
    ): LocalizationDataSource

}