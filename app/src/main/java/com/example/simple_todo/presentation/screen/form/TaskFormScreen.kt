package com.example.simple_todo.presentation.screen.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simple_todo.presentation.component.AppButton
import com.example.simple_todo.presentation.component.AppCard
import com.example.simple_todo.presentation.component.AppDivider
import com.example.simple_todo.presentation.component.AppText
import com.example.simple_todo.presentation.component.AppTextField
import com.example.simple_todo.ui.theme.BlueDark

@Composable
fun TaskFormScreen(
    viewModel: TaskFormViewModel = hiltViewModel(),
    onSaved: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AppText(
                    text = if (viewModel.isEditMode) "Editar Tarea" else "Nueva Tarea",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                AppDivider(16.dp)


                AppTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it },
                    label = "Título",
                    backgroundColor = Color.White,
                    textColor = BlueDark
                )

                AppTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    label = "Descripción",
                    backgroundColor = Color.White,
                    textColor = BlueDark
                )

                AppDivider(16.dp)


                AppButton(
                    text = if (viewModel.isEditMode) "Guardar cambios" else "Crear tarea",
                    onClick = { viewModel.saveTask(onSaved) },
                    widthFraction = 0.8f,
                    loading = uiState is TaskFormUIState.Loading,
                    enabled = uiState !is TaskFormUIState.Loading
                )


                if (uiState is TaskFormUIState.Error) {
                    AppText(
                        text = (uiState as TaskFormUIState.Error).message,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}