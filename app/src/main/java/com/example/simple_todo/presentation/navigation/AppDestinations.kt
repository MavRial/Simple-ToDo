package com.example.simple_todo.presentation.navigation

sealed class AppDestinations(val route: String) {
    object Splash : AppDestinations("splash")
    object Login : AppDestinations("login")
    object Home : AppDestinations("home")
}