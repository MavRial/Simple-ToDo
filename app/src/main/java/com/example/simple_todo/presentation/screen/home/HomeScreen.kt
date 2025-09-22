package com.example.simple_todo.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.domain.repository.TaskFilter
import com.example.simple_todo.presentation.component.AppDivider
import com.example.simple_todo.presentation.component.AppLazyColumn
import com.example.simple_todo.presentation.component.AppText
import com.example.simple_todo.presentation.component.FilterButton
import com.example.simple_todo.presentation.component.FloatingAddButton
import com.example.simple_todo.presentation.component.TaskCard
import com.example.simple_todo.ui.theme.BlueDark

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onAddTask: () -> Unit,
    onEditTask: (Task) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val filter by viewModel.filter.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppDivider(8.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilterButton("Todas", filter == TaskFilter.All) { viewModel.setFilter(TaskFilter.All) }
                FilterButton("Completas", filter == TaskFilter.Completed) { viewModel.setFilter(TaskFilter.Completed) }
                FilterButton("Incompletas", filter == TaskFilter.Incomplete) { viewModel.setFilter(TaskFilter.Incomplete) }
            }

            when (uiState) {
                is TaskUiState.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = BlueDark)
                }

                is TaskUiState.Success -> {
                    val tasks = (uiState as TaskUiState.Success).tasks
                    if (tasks.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppText("No hay tareas", fontSize = 16.sp, textAlign = TextAlign.Center)
                        }
                    } else {
                        AppLazyColumn(itemsList = tasks) { task ->
                            TaskCard(
                                task = task,
                                onToggleComplete = { viewModel.toggleTaskComplete(task) },
                                onEdit = { onEditTask(task) },
                                onDelete = { viewModel.deleteTask(task) }
                            )
                        }
                    }
                }

                is TaskUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            "Error: ${(uiState as TaskUiState.Error).message}",
                            color = Color.Red,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        FloatingAddButton(
            onClick = onAddTask,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}