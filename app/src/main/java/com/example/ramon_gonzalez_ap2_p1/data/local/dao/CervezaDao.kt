package com.example.Ramon_Gonzalez_AP2_P1.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.Ramon_Gonzalez_AP2_P1.local.entites.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao{
    @Upsert
    suspend fun save(Cerveza : CervezaEntity)
    @Delete
    suspend fun delete(Cerveza : CervezaEntity)
    @Query("SELECT * FROM cervezas WHERE id = :id")
    suspend fun find(id : Int) : CervezaEntity?

    @Query("SELECT * FROM cervezas")
    fun getAll() : Flow<List<CervezaEntity>>

}