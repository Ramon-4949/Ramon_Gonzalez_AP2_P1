package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.domain.usecase.DeleteCervezaUseCase
import com.example.ramon_gonzalez_ap2_p1.domain.usecase.GetCervezaUseCase
import com.example.ramon_gonzalez_ap2_p1.domain.usecase.UpsertCervezaUseCase
import com.example.ramon_gonzalez_ap2_p1.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CervezaViewModel @Inject constructor(
    private val upsertUseCase: UpsertCervezaUseCase,
    private val getUseCase: GetCervezaUseCase,
    private val deleteUseCase: DeleteCervezaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CervezaUiState())
    val uiState = _uiState.asStateFlow()

    private val cervezaId = savedStateHandle.toRoute<Screen.CervezaEdit>().cervezaId

    init {
        if (cervezaId != 0) {
            loadCerveza(cervezaId)
        }
    }

    private fun loadCerveza(id: Int) {
        viewModelScope.launch {
            val cerveza = getUseCase(id)
            cerveza?.let { c ->
                _uiState.update {
                    it.copy(
                        Id = c.id,
                        nombre = c.nombre,
                        marca = c.marca,
                        puntuacion = c.puntuacion.toString()
                    )
                }
            }
        }
    }

    fun onEvent(event: CervezaUiEvent) {
        when (event) {
            is CervezaUiEvent.nombreChanged -> _uiState.update {
                it.copy(nombre = event.nombre, nombreError = null)
            }
            is CervezaUiEvent.marcaChanged -> _uiState.update {
                it.copy(marca = event.marca, marcaError = null)
            }
            is CervezaUiEvent.puntuacionChanged -> _uiState.update {
                it.copy(puntuacion = event.puntuacion, puntuacionError = null)
            }
            CervezaUiEvent.Save -> save()
            CervezaUiEvent.Delete -> delete()
        }
    }

    private fun save() {
        val state = _uiState.value
        var isValid = true
        var nombreErr: String? = null
        var marcaErr: String? = null
        var puntuacionErr: String? = null

        // Validaciones
        if (state.nombre.isBlank()) {
            nombreErr = "El nombre es obligatorio"; isValid = false
        }
        if (state.marca.isBlank()) {
            marcaErr = "La marca es obligatoria"; isValid = false
        }

        val puntInt = state.puntuacion.toIntOrNull()
        if (puntInt == null || puntInt < 0 || puntInt > 10) {
            puntuacionErr = "Debe ser un número entre 0 y 10"; isValid = false
        }

        if (!isValid) {
            _uiState.update {
                it.copy(nombreError = nombreErr, marcaError = marcaErr, puntuacionError = puntuacionErr)
            }
            return
        }

        viewModelScope.launch {
            try {
                val cerveza = Cerveza(
                    id = state.Id,
                    nombre = state.nombre,
                    marca = state.marca,
                    puntuacion = puntInt!!
                )

                upsertUseCase(cerveza)
                _uiState.update { it.copy(success = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(errorGeneral = "Error al guardar: ${e.message}") }
            }
        }
    }

    private fun delete() {
        viewModelScope.launch {
            val cerveza = Cerveza(id = cervezaId, nombre = "", marca = "", puntuacion = 0)
            deleteUseCase(cerveza)
            _uiState.update { it.copy(success = true) }
        }
    }
}