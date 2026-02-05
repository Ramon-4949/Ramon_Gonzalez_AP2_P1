package com.example.ramon_gonzalez_ap2_p1.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ramon_gonzalez_ap2_p1.presentation.navigation.Screen


@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
            ListScreen(
                onNavigateToEdit = {
                    navController.navigate(Screen.BorrameEdit(0))
                }
            )
        }
        composable<Screen.BorrameEdit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.BorrameEdit>()

            EditScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}