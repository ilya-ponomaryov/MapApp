package com.example.mapapp.common.storages

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mapapp.common.storages.models.CoordinateEntity

@Database(entities = [CoordinateEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coordinateDao(): CoordinateDao
}