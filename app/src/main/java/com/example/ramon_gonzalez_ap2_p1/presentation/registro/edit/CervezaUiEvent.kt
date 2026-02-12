package com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.edit

sealed interface CervezaUiEvent {
    data class nombreChanged(val nombre: String) : CervezaUiEvent
    data class marcaChanged(val marca: String) : CervezaUiEvent
    data class puntuacionChanged(val puntuacion: String) : CervezaUiEvent
    data object Save : CervezaUiEvent
    data object Delete : CervezaUiEvent
}



