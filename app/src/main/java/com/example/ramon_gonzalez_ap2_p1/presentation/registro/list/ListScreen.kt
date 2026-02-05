package com.example.ramon_gonzalez_ap2_p1.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListScreen(
    onNavigateToEdit: () -> Unit
) {
    Column {
        Text(text = "Pantalla de Lista")
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen(onNavigateToEdit = {})
}