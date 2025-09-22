package com.example.simple_todo.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.domain.repository.TaskFilter
import com.example.simple_todo.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _filter = MutableStateFlow(TaskFilter.All)
    val filter: StateFlow<TaskFilter> = _filter.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<TaskUiState> = _filter
        .flatMapLatest { filter ->
            taskUseCases.getTasks(filter)
                .map { TaskUiState.Success(it) as TaskUiState }
                .catch { emit(TaskUiState.Error(it.message ?: "Error")) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, TaskUiState.Loading)

    fun setFilter(filter: TaskFilter) {
        _filter.value = filter
    }

    fun toggleTaskComplete(task: Task) = viewModelScope.launch {
        taskUseCases.updateTask(task.copy(isCompleted = !task.isCompleted, pendingSync = true))
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskUseCases.deleteTask(task)
    }
}
