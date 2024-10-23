package com.dev.datasource.roomDatabase.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dev.datasource.roomDatabase.db.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "my_datatstore"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        fun providesPreferenceDataStore(
            @ApplicationContext context: Context
        ): DataStore<Preferences> {
            return context.dataStore
        }

        @Singleton
        @Provides
        fun providesDatabase(@ApplicationContext context: Context): MyRoomDatabase {
            return MyRoomDatabase.getInstance(context)
        }

        @Singleton
        @Provides
        fun providesUserInfoDao(database: MyRoomDatabase) = database.userInfoDao()

    }
}