package com.example.simple_todo.presentation.screen.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.domain.repository.TaskFilter
import com.example.simple_todo.domain.usecase.TaskUseCases
import com.example.simple_todo.domain.usecase.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskFormViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskId: String? = savedStateHandle["taskId"]
    var isEditMode: Boolean = taskId != null

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    private var editTask: Task? = null

    private val _uiState = MutableStateFlow<TaskFormUIState>(TaskFormUIState.Idle)
    val uiState: StateFlow<TaskFormUIState> = _uiState.asStateFlow()

    init {
        if (isEditMode) loadTask()
    }

    private fun loadTask() = viewModelScope.launch {
        _uiState.value = TaskFormUIState.Loading
        try {
            val currentUser = userUseCases.getUser() ?: throw IllegalStateException("No user logged in")

            taskUseCases.getTasks(TaskFilter.All)
                .map { list -> list.firstOrNull { it.id == taskId && it.userId == currentUser.uid } }
                .collect { task ->
                    if (task != null) {
                        editTask = task
                        title = task.title
                        description = task.description
                        _uiState.value = TaskFormUIState.Idle
                    } else {
                        _uiState.value = TaskFormUIState.Error("Tarea no encontrada")
                    }
                }

        } catch (e: Exception) {
            _uiState.value = TaskFormUIState.Error(e.message ?: "Error cargando tarea")
        }
    }

    fun saveTask(onSaved: () -> Unit) = viewModelScope.launch {
        if (title.isBlank()) {
            _uiState.value = TaskFormUIState.Error("El título no puede estar vacío")
            return@launch
        }

        _uiState.value = TaskFormUIState.Loading
        try {
            val currentUser = userUseCases.getUser() ?: throw Exception("No user logged in")

            val task = Task(
                id = taskId ?: UUID.randomUUID().toString(),
                userId = currentUser.uid,
                title = title,
                description = description,
                isCompleted = editTask?.isCompleted ?: false,
                pendingSync = true
            )

            if (isEditMode) taskUseCases.updateTask(task)
            else taskUseCases.addTask(task)

            _uiState.value = TaskFormUIState.Success
            onSaved()
        } catch (e: Exception) {
            _uiState.value = TaskFormUIState.Error(e.message ?: "Error guardando tarea")
        }
    }
}
