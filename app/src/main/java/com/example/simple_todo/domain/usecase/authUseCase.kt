package com.example.simple_todo.domain.usecase

import com.example.simple_todo.domain.repository.AuthRepository


class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.register(email, password)
}

class LogoutUseCase(private val repository: AuthRepository){
    suspend operator fun invoke() = repository.logout()
}

class GetCurrentUserUseCase(private val repository: AuthRepository){
    operator fun invoke() = repository.getCurrentUid()
}

data class AuthUseCases(
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val logout: LogoutUseCase,
    val getCurrentUser: GetCurrentUserUseCase
)