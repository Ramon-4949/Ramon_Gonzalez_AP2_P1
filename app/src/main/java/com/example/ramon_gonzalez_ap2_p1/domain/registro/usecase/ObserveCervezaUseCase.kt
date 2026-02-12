package com.example.Ramon_Gonzalez_AP2_P1.usecase

import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCervezaaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(): Flow<List<Cerveza>> {
        return repository.getAll()
    }
}