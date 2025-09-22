package com.example.simple_todo.domain.repository

import com.example.simple_todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasksFlow(filter: TaskFilter = TaskFilter.All): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun syncPendingTasks()
}

enum class TaskFilter { All, Completed, Incomplete }