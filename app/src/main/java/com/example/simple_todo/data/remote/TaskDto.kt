package com.example.simple_todo.data.remote

data class TaskDto(
    val id: String? = null,
    val userId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val isCompleted: Boolean? = null,
    val pendingSync: Boolean? = null
)