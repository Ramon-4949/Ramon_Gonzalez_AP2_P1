package com.example.ramon_gonzalez_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey
    val id: Int? = null,
    val descripcion: String = ""
)