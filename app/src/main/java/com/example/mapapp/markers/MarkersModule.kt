package com.example.mapapp.markers

import com.example.mapapp.common.storages.CoordinateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarkersModule {

    @Provides
    @Singleton
    fun provideMarkersRepository(coordinateDao: CoordinateDao): MarkersRepository =
        MarkersRepositoryImpl(coordinateDao)

    @Provides
    @Singleton
    fun provideMarkersGetter(repository: MarkersRepository): MarkersGetter =
        MarkersGetterImpl(repository)
}