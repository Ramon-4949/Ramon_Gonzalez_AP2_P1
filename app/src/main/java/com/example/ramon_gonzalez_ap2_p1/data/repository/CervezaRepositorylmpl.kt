package com.example.Ramon_Gonzalez_AP2_P1.data.repository

import com.example.Ramon_Gonzalez_AP2_P1.data.mapper.toDomain
import com.example.Ramon_Gonzalez_AP2_P1.data.mapper.toEntity
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.local.dao.CervezaDao
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
class CervezaRepositorylmpl @Inject constructor(
    private val dao: CervezaDao
) : CervezaRepository {

    override suspend fun save(entrada: Cerveza) {
        dao.save(entrada.toEntity())
    }

    override suspend fun delete(entrada: Cerveza) {
        dao.delete(entrada.toEntity())
    }

    override suspend fun getEntrada(id: Int): Cerveza? {
        return dao.find(id)?.toDomain()
    }

    override fun getAll(): Flow<List<Cerveza>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}