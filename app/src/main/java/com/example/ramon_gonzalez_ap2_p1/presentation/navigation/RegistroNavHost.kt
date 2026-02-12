package com.example.Ramon_Gonzalez_AP2_P1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.Ramon_Gonzalez_AP2_P1.presentation.registro.list.CervezaListScreen


@Composable
fun CervesaNavHost(
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
            EditCervezaScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}