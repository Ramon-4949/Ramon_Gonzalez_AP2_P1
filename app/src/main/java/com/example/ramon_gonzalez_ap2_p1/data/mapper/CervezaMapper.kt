package com.example.ramon_gonzalez_ap2_p1.data.mapper

import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.local.entites.CervezaEntity

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