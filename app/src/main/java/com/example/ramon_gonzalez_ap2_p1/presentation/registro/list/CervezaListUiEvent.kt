package com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.list

import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza

sealed interface CervezaListUiEvent {
    data class OnFilterChange(val filtro: String) : CervezaListUiEvent
    data class OnDelete(val Cerveza: Cerveza) : CervezaListUiEvent
}