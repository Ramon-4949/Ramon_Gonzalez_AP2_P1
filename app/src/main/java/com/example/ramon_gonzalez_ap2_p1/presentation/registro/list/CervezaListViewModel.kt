package com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.repository.CervezaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CervezaListViewModel @Inject constructor(
    private val repository: CervezaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CervezaListUiState())
    val uiState = _uiState.asStateFlow()

    private val _entradasRaw = repository.getAll()

    init {
        viewModelScope.launch {
            combine(_entradasRaw, _uiState) { lista, estado ->
                val listaFiltrada = if (estado.filtro.isBlank()) {
                    lista
                } else {
                    lista.filter {
                        it.nombre.contains(estado.filtro, ignoreCase = true)
                    }
                }

                val totalDinero = listaFiltrada.sumOf { it.total }
                val totalItems = listaFiltrada.sumOf { it.cantidad }

                estado.copy(
                    cervezas = listaFiltrada,
                    conteoTotal = totalItems,
                    importeTotal = totalDinero,
                    isLoading = false
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    fun onEvent(event: CervezaListUiEvent) {
        when (event) {
            is CervezaListUiEvent.OnFilterChange -> {
                _uiState.update { it.copy(filtro = event.filtro) }
            }
            is CervezaListUiEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.delete(event.cerveza)
                }
            }
        }
    }
}