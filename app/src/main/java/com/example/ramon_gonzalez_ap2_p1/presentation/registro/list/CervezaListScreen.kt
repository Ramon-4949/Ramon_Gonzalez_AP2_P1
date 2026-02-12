package com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.list

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListScreen(
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    viewModel: CervezaListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Entradas de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        bottomBar = {
            BottomTotalBar(
                cantidad = state.conteoTotal,
                dinero = state.importeTotal
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.filtro,
                onValueChange = { viewModel.onEvent(CervezaListUiEvent.OnFilterChange(it)) },
                label = { Text("Buscar por Cliente") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.cerveza) { entrada ->
                    CervezaItem(
                        entrada = entrada,
                        onClick = { onNavigateToEdit(entrada.id) },
                        onDelete = { viewModel.onEvent(CervezaListUiEvent.OnDelete(entrada)) }
                    )
                }
            }
        }
    }
}


@Composable
fun BottomTotalBar(cantidad: Int, dinero: Double) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

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
            text = "Total: ${currencyFormat.format(dinero)}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}