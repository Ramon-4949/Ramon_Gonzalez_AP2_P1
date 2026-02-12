package com.example.ramon_gonzalez_ap2_p1.usecase

import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.domain.registro.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class ObserveCervezaaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(): Flow<List<Cerveza>> {
        return repository.getAll()
    }
}