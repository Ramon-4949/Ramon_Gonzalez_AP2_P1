package com.example.ramon_gonzalez_ap2_p1.presentation.registro.list

import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza


data class CervezaListUiState(
    val isLoading: Boolean = false,
    val cerveza: List<Cerveza> = emptyList(),
    val filtro: String = "",
    val error: String? = null,
    val conteoTotal: Int = 0,
    val promedioTotal: Double = 0.0
)