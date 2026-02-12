package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

sealed interface CervezaUiEvent {
    data class nombreChanged(val nombre: String) : CervezaUiEvent
    data class marcaChanged(val marca: String) : CervezaUiEvent
    data class puntuacionChanged(val puntuacion: String) : CervezaUiEvent
    data object Save : CervezaUiEvent
    data object Delete : CervezaUiEvent
}



