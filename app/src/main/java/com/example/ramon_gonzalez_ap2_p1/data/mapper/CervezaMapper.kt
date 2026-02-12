package com.example.Ramon_Gonzalez_AP2_P1.data.mapper


import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.local.entites.CervezaEntity


fun CervezaEntity.toDomain() = Cerveza(
    id = id,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)

fun Cerveza.toEntity() = CervezaEntity(
    id = id,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)