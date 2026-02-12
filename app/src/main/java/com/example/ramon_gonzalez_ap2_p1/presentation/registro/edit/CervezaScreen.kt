package com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CervezaScreen(
    viewModel: CervezaViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onNavigateBack()
        }
    }

    CervezaBody(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaBody(
    uiState: CervezaUiState,
    onEvent: (CervezaUiEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (uiState.Id == 0) "Crear Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
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
            // Nombre
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.nombre,
                onValueChange = { onEvent(CervezaUiEvent.nombreChanged(it)) },
                label = { Text("Nombre") },
                isError = uiState.nombreError != null,
                supportingText = { uiState.nombreError?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
            )

            // Marca
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.marca,
                onValueChange = { onEvent(CervezaUiEvent.marcaChanged(it)) },
                label = { Text("Marca") },
                isError = uiState.marcaError != null,
                supportingText = { uiState.marcaError?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
            )

            // Puntuacion
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.puntuacion,
                onValueChange = { onEvent(CervezaUiEvent.puntuacionChanged(it)) },
                label = { Text("Puntuación (0-10)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.puntuacionError != null,
                supportingText = { uiState.puntuacionError?.let { Text(it, color = MaterialTheme.colorScheme.error) } }
            )

            if (uiState.errorGeneral != null) {
                Text(uiState.errorGeneral, color = MaterialTheme.colorScheme.error)
            }

            if (uiState.Id != 0) {
                OutlinedButton(
                    onClick = { onEvent(CervezaUiEvent.Delete) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Text("Eliminar")
                }
            }
        }
    }
}