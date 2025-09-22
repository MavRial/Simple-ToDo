package com.example.simple_todo.domain.usecase

import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.domain.repository.TaskFilter
import com.example.simple_todo.domain.repository.TaskRepository

data class TaskUseCases(
    val getTasks: GetTasksUseCase,
    val addTask: AddTaskUseCase,
    val updateTask: UpdateTaskUseCase,
    val deleteTask: DeleteTaskUseCase,
    val syncPending: SyncPendingTasksUseCase
)

class GetTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(filter: TaskFilter = TaskFilter.All) = repository.getTasksFlow(filter)
}

class AddTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}

class UpdateTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.updateTask(task)
}

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.deleteTask(task)
}

class SyncPendingTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke() = repository.syncPendingTasks()
}