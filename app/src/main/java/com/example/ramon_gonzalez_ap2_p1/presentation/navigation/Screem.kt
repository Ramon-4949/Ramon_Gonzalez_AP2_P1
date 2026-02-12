package com.example.Ramon_Gonzalez_AP2_P1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object EntradaList : Screen()

    @Serializable
    data class EntradaEdit(val entradaId: Int) : Screen()
}