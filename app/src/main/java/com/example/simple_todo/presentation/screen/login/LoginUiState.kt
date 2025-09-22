package com.example.simple_todo.presentation.screen.login

sealed class LoginUIState {
    object Idle : LoginUIState()
    object Loading : LoginUIState()
    object Success : LoginUIState()
    data class Error(val message: String) : LoginUIState()
}