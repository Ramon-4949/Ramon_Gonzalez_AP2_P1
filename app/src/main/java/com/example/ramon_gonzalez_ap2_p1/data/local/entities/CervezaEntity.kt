package com.example.ramon_gonzalez_ap2_p1.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cervezas")
data class CervezaEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val nombre : String,
    val marca : String,
    val puntuacion : Int,
)