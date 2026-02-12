package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.edit.CervezaUiEvent

@Composable
fun EditEntradaScreen(
    viewModel: CervezaViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onNavigateBack()
        }
    }

    EditEntradaBody(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEntradaBody(
    uiState: CervezaUiState,
    onEvent: (CervezaUiEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (uiState.Id == 0) "Crear Entrada" else "Editar Entrada") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(CervezaUiEvent.Save) }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Campo Cliente (Con validación estilo ejemplo)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.nombre,
                onValueChange = { onEvent(CervezaUiEvent.nombreChanged(it)) },
                label = { Text("Nombre del Cliente") },
                isError = uiState.nombreError != null,
                supportingText = {
                    uiState.nombreError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.marca,
                onValueChange = { onEvent(CervezaUiEvent.marcaChanged(it)) },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.marcaError != null,
                supportingText = {
                    uiState.marcaError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.puntuacion,
                onValueChange = { onEvent(CervezaUiEvent.puntuacionChanged(it)) },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = uiState.puntuacionError != null,
                supportingText = {
                    uiState.puntuacion?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            )

            val importe = (uiState.marca.toIntOrNull() ?: 0) * (uiState.puntuacion.toDoubleOrNull() ?: 0.0)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = importe.toString(),
                onValueChange = {},
                label = { Text("Importe Total") },
                readOnly = true
            )

            // Error General (si falla la BD)
            if (uiState.errorGeneral != null) {
                Text(
                    text = uiState.errorGeneral,
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (uiState.Id != 0) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { onEvent(CervezaUiEvent.Delete) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Eliminar")
                }
            }
        }
    }
}