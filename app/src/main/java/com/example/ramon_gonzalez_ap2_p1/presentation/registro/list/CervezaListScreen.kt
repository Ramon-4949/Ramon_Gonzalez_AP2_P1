package com.example.ramon_gonzalez_ap2_p1.presentation.registro.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ramon_gonzalez_ap2_p1.domain.registro.model.Cerveza
import com.example.ramon_gonzalez_ap2_p1.ui.theme.Ramon_Gonzalez_AP2_P1Theme

@Composable
fun CervezaListScreen(
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    viewModel: CervezaListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CervezaListBody(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateToCreate = onNavigateToCreate,
        onNavigateToEdit = onNavigateToEdit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListBody(
    state: CervezaListUiState,
    onEvent: (CervezaListUiEvent) -> Unit,
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Lista de Cervezas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        bottomBar = {
            BottomTotalBar(
                cantidad = state.conteoTotal,
                promedio = state.promedioTotal
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Filtro
            OutlinedTextField(
                value = state.filtro,
                onValueChange = { onEvent(CervezaListUiEvent.OnFilterChange(it)) },
                label = { Text("Buscar por nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.cerveza.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay datos guardados",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.cerveza) { item ->
                        CervezaItem(
                            cerveza = item,
                            onClick = { onNavigateToEdit(item.id) },
                            onDelete = { onEvent(CervezaListUiEvent.OnDelete(item)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CervezaItem(
    cerveza: Cerveza,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cerveza.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Marca: ${cerveza.marca}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Puntos: ${cerveza.puntuacion}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}

@Composable
fun BottomTotalBar(cantidad: Int, promedio: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Cant: $cantidad",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Promedio: ${String.format("%.1f", promedio)}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, name = "Lista Con Datos")
@Composable
fun CervezaListPreviewDatos() {
    Ramon_Gonzalez_AP2_P1Theme {
        CervezaListBody(
            state = CervezaListUiState(
                cerveza = listOf(
                    Cerveza(1, "Presidente", "CN", 10),
                    Cerveza(2, "Corona", "Modelo", 8)
                ),
                conteoTotal = 2,
                promedioTotal = 9.0
            ),
            onEvent = {},
            onNavigateToCreate = {},
            onNavigateToEdit = {}
        )
    }
}

@Preview(showBackground = true, name = "Lista Vacía (Mensaje)")
@Composable
fun CervezaListPreviewVacia() {
    Ramon_Gonzalez_AP2_P1Theme {
        CervezaListBody(
            state = CervezaListUiState(
                cerveza = emptyList(),
                conteoTotal = 0,
                promedioTotal = 0.0
            ),
            onEvent = {},
            onNavigateToCreate = {},
            onNavigateToEdit = {}
        )
    }
}