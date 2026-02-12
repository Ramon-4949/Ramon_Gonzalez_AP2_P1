package com.example.ramon_gonzalez_ap2_p1.presentation.registro.list

import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza

sealed interface CervezaListUiEvent {
    data class OnFilterChange(val filtro: String) : CervezaListUiEvent
    data class OnDelete(val Cerveza: Cerveza) : CervezaListUiEvent
}