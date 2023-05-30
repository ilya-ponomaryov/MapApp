package com.example.mapapp.common.storages.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coordinate")
data class CoordinateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val latitude: Double,
    val longitude: Double,
)


