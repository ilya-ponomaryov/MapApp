package com.example.mapapp.common.storages

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "AppDatabase"
    ).build()

    @Provides
    @Singleton
    fun provideCoordinateDao(appDatabase: AppDatabase): CoordinateDao = appDatabase.coordinateDao()
}