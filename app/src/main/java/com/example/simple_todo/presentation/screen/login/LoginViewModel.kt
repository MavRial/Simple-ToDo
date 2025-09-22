package com.example.simple_todo.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_todo.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()


    private fun validateCredentials(email: String, password: String): String? {
        if (email.isBlank()) return "El correo no puede estar vacío"
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "El correo es inválido"
        if (password.isBlank()) return "La contraseña no puede estar vacía"
        if (password.length < 6) return "La contraseña debe tener al menos 6 caracteres"
        return null
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        val validationError = validateCredentials(email, password)
        if (validationError != null) {
            _uiState.value = LoginUIState.Error(validationError)
            return@launch
        }

        _uiState.value = LoginUIState.Loading

        val result = authUseCases.login(email, password)
        _uiState.value = if (result.isSuccess) {
            LoginUIState.Success
        } else {
            LoginUIState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
        }
    }

    fun register(email: String, password: String) = viewModelScope.launch {
        val validationError = validateCredentials(email, password)
        if (validationError != null) {
            _uiState.value = LoginUIState.Error(validationError)
            return@launch
        }

        _uiState.value = LoginUIState.Loading

        val result = authUseCases.register(email, password)
        _uiState.value = if (result.isSuccess) {
            LoginUIState.Success
        } else {
            LoginUIState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
        }
    }
}