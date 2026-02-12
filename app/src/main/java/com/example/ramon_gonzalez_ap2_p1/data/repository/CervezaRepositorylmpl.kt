package com.example.ramon_gonzalez_ap2_p1.data.repository

import com.example.ramon_gonzalez_ap2_p1.data.mapper.toDomain
import com.example.ramon_gonzalez_ap2_p1.data.mapper.toEntity
import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.domain.registro.repository.CervezaRepository
import com.example.ramon_gonzalez_ap2_p1.local.dao.CervezaDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val dao: CervezaDao
) : CervezaRepository {

    override suspend fun save(cerveza: Cerveza) {
        dao.save(cerveza.toEntity())
    }

    override suspend fun delete(cerveza: Cerveza) {
        dao.delete(cerveza.toEntity())
    }

    override suspend fun getCerveza(id: Int): Cerveza? {
        return dao.find(id)?.toDomain()
    }

    override fun getAll(): Flow<List<Cerveza>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}