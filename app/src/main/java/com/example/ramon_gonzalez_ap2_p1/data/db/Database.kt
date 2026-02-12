package com.example.Ramon_Gonzalez_AP2_P1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.Ramon_Gonzalez_AP2_P1.local.dao.CervezaDao
import com.example.Ramon_Gonzalez_AP2_P1.local.entites.CervezaEntity

@Database(
    entities = [CervezaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CervezaDao(): CervezaDao
}

