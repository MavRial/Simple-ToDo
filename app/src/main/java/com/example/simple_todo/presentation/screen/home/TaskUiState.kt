package com.example.simple_todo.presentation.screen.home

import com.example.simple_todo.domain.model.Task

sealed class TaskUiState {
    object Loading : TaskUiState()
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
}