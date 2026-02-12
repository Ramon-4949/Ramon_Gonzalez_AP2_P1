package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.Ramon_Gonzalez_AP2_P1.domain.registro.model.Cerveza
import com.example.Ramon_Gonzalez_AP2_P1.domain.usecase.DeleteCervezaUseCase
import com.example.Ramon_Gonzalez_AP2_P1.domain.usecase.GetCervezaUseCase
import com.example.Ramon_Gonzalez_AP2_P1.domain.usecase.SaveCervezaUseCase
import com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.edit.CervezaUiEvent
import com.example.ramon_gonzalez_ap2_p1.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CervezaViewModel @Inject constructor(
    private val saveUseCase: SaveCervezaUseCase,
    private val getUseCase: GetCervezaUseCase,
    private val deleteUseCase: DeleteCervezaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CervezaUiState())
    val uiState = _uiState.asStateFlow()
    private val Id = savedStateHandle.toRoute<Screen.EntradaEdit>().entradaId
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        if (Id != 0) {
            loadEntrada(Id)
        } else {
        }
    }

    private fun loadEntrada(id: Int) {
        viewModelScope.launch {
            val entrada = getUseCase(id)
            entrada?.let { e ->
                _uiState.update {
                    it.copy(
                        Id = e.id,
                        nombre = e.nombre,
                        marca = e.marca.toString(),
                        puntuacion = e.puntuacion.toString()
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

        // --- 1. VALIDACIÓN EN EL VIEWMODEL ---
        var isValid = true
        var nombreError: String? = null
        var cantidadError: String? = null
        var precioError: String? = null

        val cant = state.marca.toIntOrNull() ?: 0
        val prec = state.puntuacion.toDoubleOrNull() ?: 0.0

        if (state.nombre.isBlank()) {
            nombreError = "El nombre es obligatorio"
            isValid = false
        }
        if (cant <= 0) {
            cantidadError = "La cantidad debe ser mayor a 0"
            isValid = false
        }
        if (prec <= 0.0) {
            precioError = "El precio debe ser mayor a 0"
            isValid = false
        }

        // Si hay errores, actualizamos el estado y NO guardamos
        if (!isValid) {
            _uiState.update {
                it.copy(
                    nombreError = nombreError,
                    marcaError = cantidadError,
                    puntuacionError = precioError
                )
            }
            return
        }

        viewModelScope.launch {
            try {
                val fechaDate = try {
                } catch (e: Exception) {
                    Date()
                }

                val entrada = Cerveza(
                    id = state.Id,
                    nombre = state.nombre,
                    marca = cant,
                    precio = prec
                )

                saveUseCase(entrada)

                _uiState.update { it.copy(success = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(errorGeneral = "Error al guardar: ${e.message}") }
            }
        }
    }

    private fun delete() {
        viewModelScope.launch {
            val entrada = Cerveza(
                id = _uiState.value.Id,
                nombre = "", marca = 0, puntuacion = 0.0
            )
            deleteUseCase(entrada)
            _uiState.update { it.copy(success = true) }
        }
    }
}