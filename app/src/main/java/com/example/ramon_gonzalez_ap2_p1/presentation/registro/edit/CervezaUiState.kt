package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

data class CervezaUiState(
    val Id: Int = 0,
    val nombre: String = "",
    val marca: String = "",
    val puntuacion: String = "",

    val nombreError: String? = null,
    val marcaError: String? = null,
    val puntuacionError: String? = null,
    val errorGeneral: String? = null,

    val isLoading: Boolean = false,
    val success: Boolean = false
)