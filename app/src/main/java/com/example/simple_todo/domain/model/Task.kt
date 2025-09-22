package com.example.simple_todo.domain.model

data class Task(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val pendingSync: Boolean = true
)