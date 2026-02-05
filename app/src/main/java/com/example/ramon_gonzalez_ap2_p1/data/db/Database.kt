package com.example.ramon_gonzalez_ap2_p1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ramon_gonzalez_ap2_p1.data.local.dao.BorrameDao
import com.example.ramon_gonzalez_ap2_p1.data.local.entities.BorrameEntity

@Database(
    entities = [BorrameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun borrameDao(): BorrameDao
}