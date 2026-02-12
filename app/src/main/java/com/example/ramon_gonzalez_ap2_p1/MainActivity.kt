package com.example.ramon_gonzalez_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.ramon_gonzalez_ap2_p1.presentation.navigation.RegistroNavHost
import com.example.ramon_gonzalez_ap2_p1.ui.theme.Ramon_Gonzalez_AP2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ramon_Gonzalez_AP2_P1Theme {
                val navController = rememberNavController()
                RegistroNavHost(navController = navController)
            }
        }
    }
}