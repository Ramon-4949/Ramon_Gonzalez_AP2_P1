package com.example.ramon_gonzalez_ap2_p1.presentation.registro.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramon_gonzalez_ap2_p1.domain.registro.repository.CervezaRepository
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
    private val _cervezasRaw = repository.getAll()

    init {
        viewModelScope.launch {
            combine(_cervezasRaw, _uiState) { lista, estado ->
                // 1. Filtrar
                val listaFiltrada = if (estado.filtro.isBlank()) {
                    lista
                } else {
                    lista.filter {
                        it.nombre.contains(estado.filtro, ignoreCase = true)
                    }
                }

                val conteo = listaFiltrada.size
                val promedio = if (conteo > 0) {
                    listaFiltrada.sumOf { it.puntuacion } / conteo.toDouble()
                } else {
                    0.0
                }

                estado.copy(
                    cerveza = listaFiltrada,
                    conteoTotal = conteo,
                    promedioTotal = promedio,
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
                    repository.delete(event.Cerveza)
                }
            }
        }
    }
}