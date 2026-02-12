package com.example.ramon_gonzalez_ap2_p1.domain.registro.repository

import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    suspend fun save(cerveza: Cerveza)
    suspend fun delete(cerveza: Cerveza)
    suspend fun getCerveza(id: Int): Cerveza?
    fun getAll(): Flow<List<Cerveza>>
}