package com.example.simple_todo.presentation.screen.login

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.simple_todo.ui.theme.BlueSecondary
import com.example.simple_todo.ui.theme.WhiteText

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                    text = "Bienvenido",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )


                AppText(
                    text = "Inicia sesión o regístrate para continuar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = BlueDark.copy(alpha = 0.7f),
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )

                AppDivider(16.dp)


                AppTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico",
                    backgroundColor = Color.White,
                    textColor = BlueDark
                )


                AppTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    isPassword = true,
                    backgroundColor = Color.White,
                    textColor = BlueDark
                )

                AppDivider(16.dp)


                AppButton(
                    text = "Iniciar Sesión",
                    onClick = { viewModel.login(email, password) },
                    widthFraction = 0.8f,
                    loading = uiState is LoginUIState.Loading,
                    enabled = uiState !is LoginUIState.Loading
                )


                AppButton(
                    text = "Registrarse",
                    onClick = { viewModel.register(email, password) },
                    widthFraction = 0.8f,
                    backgroundColor = BlueSecondary,
                    contentColor = WhiteText,
                    loading = uiState is LoginUIState.Loading,
                    enabled = uiState !is LoginUIState.Loading
                )


                when (uiState) {
                    is LoginUIState.Error -> {
                        AppText(
                            text = (uiState as LoginUIState.Error).message,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                    is LoginUIState.Success -> {
                        LaunchedEffect(Unit) { onLoginSuccess() }
                    }
                    else -> {}
                }
            }
        }
    }
}