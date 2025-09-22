package com.example.simple_todo.presentation.screen.form

sealed class TaskFormUIState {
    object Idle : TaskFormUIState()
    object Loading : TaskFormUIState()
    object Success : TaskFormUIState()
    data class Error(val message: String) : TaskFormUIState()
}