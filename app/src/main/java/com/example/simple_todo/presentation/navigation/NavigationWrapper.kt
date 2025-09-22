package com.example.simple_todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simple_todo.presentation.screen.home.HomeScreen
import com.example.simple_todo.presentation.screen.login.LoginScreen
import com.example.simple_todo.presentation.screen.splash.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AppDestinations.Splash.route) {
        composable(AppDestinations.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(AppDestinations.Login.route) {
                        popUpTo(AppDestinations.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(AppDestinations.Home.route) {
                    popUpTo(AppDestinations.Login.route) { inclusive = true }
                }
            })
        }

        composable(AppDestinations.Home.route) {
            HomeScreen()
        }
    }
}