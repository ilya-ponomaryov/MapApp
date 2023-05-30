package com.example.mapapp.common.coordinate

import com.example.mapapp.common.storages.CoordinateDao
import com.example.mapapp.common.usecases.CoordinateSetter
import com.example.mapapp.common.usecases.CoordinateSetterImpl
import com.example.mapapp.common.usecases.CoordinatesGetter
import com.example.mapapp.common.usecases.CoordinatesGetterImpl
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