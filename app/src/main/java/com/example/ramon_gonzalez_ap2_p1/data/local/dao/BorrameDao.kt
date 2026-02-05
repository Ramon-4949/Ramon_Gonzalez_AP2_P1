package com.example.ramon_gonzalez_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.ramon_gonzalez_ap2_p1.data.local.entities.BorrameEntity

@Dao
interface BorrameDao {
    @Upsert
    suspend fun save(borrame: BorrameEntity)
}