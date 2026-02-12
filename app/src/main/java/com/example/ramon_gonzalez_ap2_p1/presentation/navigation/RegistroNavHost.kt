package com.example.ramon_gonzalez_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ramon_gonzalez_ap2_p1.presentation.registro.list.CervezaListScreen
import com.example.ramon_gonzalez_ap2_p1.presentation.registro.edit.CervezaScreen

@Composable
fun RegistroNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CervezaList
    ) {
        // Pantalla Lista
        composable<Screen.CervezaList> {
            CervezaListScreen(
                onNavigateToCreate = {
                    navController.navigate(Screen.CervezaEdit(0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.CervezaEdit(id))
                }
            )
        }
        // Pantalla Editar
        composable<Screen.CervezaEdit> {
            CervezaScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}