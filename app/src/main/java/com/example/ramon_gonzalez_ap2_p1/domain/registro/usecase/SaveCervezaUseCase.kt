package com.example.ramon_gonzalez_ap2_p1.domain.usecase


import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.domain.registro.repository.CervezaRepository
import javax.inject.Inject
class SaveCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza) {
        repository.save(cerveza)
    }
}