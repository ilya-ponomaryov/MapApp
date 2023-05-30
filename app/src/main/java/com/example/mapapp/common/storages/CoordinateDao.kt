package com.example.mapapp.common.storages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mapapp.common.storages.models.CoordinateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoordinateDao {
    @Query("SELECT * FROM coordinate")
    fun getAllCoordinates(): Flow<List<CoordinateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinate(coordinate: CoordinateEntity)
}