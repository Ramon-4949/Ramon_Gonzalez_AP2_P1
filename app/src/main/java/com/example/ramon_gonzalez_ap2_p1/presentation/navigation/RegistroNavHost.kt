package com.example.ramon_gonzalez_ap2_p1.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object ListScreenRoute

@Serializable
object EditScreenRoute

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ListScreenRoute
    ) {
        composable<ListScreenRoute> {
            ListScreen(
                onNavigateToEdit = {
                    navController.navigate(EditScreenRoute)
                }
            )
        }

        composable<EditScreenRoute> {
            EditScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}