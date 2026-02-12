package com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.list

import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza


data class CervezaListUiState(
    val isLoading: Boolean = false,
    val cerveza: List<Cerveza> = emptyList(),
    val filtro: String = "",
    val error: String? = null,
    val conteoTotal: Int = 0,
    val promedioTotal: Double = 0.0
)