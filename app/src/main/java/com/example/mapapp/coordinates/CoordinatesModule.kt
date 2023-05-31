package com.example.mapapp.coordinates

import com.example.mapapp.common.usecases.coordinates.CoordinateRepository
import com.example.mapapp.common.usecases.coordinates.CoordinateRepositoryImpl
import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.coordinates.usecases.CoordinateSetter
import com.example.mapapp.coordinates.usecases.CoordinateSetterImpl
import com.example.mapapp.common.usecases.coordinates.CoordinatesGetter
import com.example.mapapp.common.usecases.coordinates.CoordinatesGetterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoordinatesModule {

    @Provides
    @Singleton
    fun provideCoordinateRepository(coordinateDao: CoordinateDao): CoordinateRepository =
        CoordinateRepositoryImpl(coordinateDao)

    @Provides
    @Singleton
    fun provideCoordinateSetter(repository: CoordinateRepository): CoordinateSetter =
        CoordinateSetterImpl(repository)

    @Provides
    @Singleton
    fun provideCoordinatesGetter(repository: CoordinateRepository): CoordinatesGetter =
        CoordinatesGetterImpl(repository)
}