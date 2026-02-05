package com.example.ramon_gonzalez_ap2_p1.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditScreen(
    onNavigateBack: () -> Unit
) {
    Column {
        Text(text = "Pantalla de Edición")
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreen(onNavigateBack = {})
}