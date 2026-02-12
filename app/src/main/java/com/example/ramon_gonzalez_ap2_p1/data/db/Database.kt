package com.example.ramon_gonzalez_ap2_p1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ramon_gonzalez_ap2_p1.local.dao.CervezaDao
import com.example.ramon_gonzalez_ap2_p1.local.entites.CervezaEntity

@Database(
    entities = [CervezaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CervezaDao(): CervezaDao


}

