package com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository


import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    suspend fun save (entrada : Cerveza)
    suspend fun delete(entrada : Cerveza)
    suspend fun getEntrada(id : Int) : Cerveza?
    fun getAll(): Flow<List<Cerveza>>

}