package com.example.simple_todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simple_todo.presentation.screen.form.TaskFormScreen
import com.example.simple_todo.presentation.screen.home.HomeScreen
import com.example.simple_todo.presentation.screen.login.LoginScreen
import com.example.simple_todo.presentation.screen.splash.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AppDestinations.Splash.route) {
        // Splash
        composable(AppDestinations.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(AppDestinations.Login.route) {
                        popUpTo(AppDestinations.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Login
        composable(AppDestinations.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(AppDestinations.Home.route) {
                    popUpTo(AppDestinations.Login.route) { inclusive = true }
                }
            })
        }

        // Home
        composable(AppDestinations.Home.route) {
            HomeScreen(
                onAddTask = { navController.navigate(AppDestinations.AddTask.route) },
                onEditTask = { task ->
                    navController.navigate("${AppDestinations.EditTask.route}/${task.id}")
                }
            )
        }


        composable(AppDestinations.AddTask.route) {
            TaskFormScreen(
                onSaved = { navController.popBackStack() }
            )
        }

        // Edit Task
        composable(
            route = "${AppDestinations.EditTask.route}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            TaskFormScreen(
                onSaved = { navController.popBackStack() }
            )
        }
    }
}
