package com.example.ramon_gonzalez_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object BorrameList : Screen()
    @Serializable
    data class BorrameEdit(val borrameId: Int) : Screen()
}