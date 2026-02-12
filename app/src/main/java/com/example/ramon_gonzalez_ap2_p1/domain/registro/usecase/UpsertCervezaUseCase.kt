package com.example.Ramon_Gonzalez_AP2_P1.domain.usecase

import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository.CervezaRepository
import javax.inject.Inject
class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(entrada: Cerveza) = repository.save(entrada)
}