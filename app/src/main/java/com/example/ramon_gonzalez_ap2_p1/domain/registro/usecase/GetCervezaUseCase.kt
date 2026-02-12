package com.example.Ramon_Gonzalez_AP2_P1.domain.usecase

import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository.CervezaRepository
import javax.inject.Inject

class GetCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int): Cerveza? {
        return repository.getEntrada(id)
    }
}