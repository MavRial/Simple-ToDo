package com.example.simple_todo.domain.usecase

import com.example.simple_todo.domain.model.User
import com.example.simple_todo.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUser()
}

class SaveUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.saveUser(user)
}

class ClearUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.clearUser()
}

data class UserUseCases(
    val getUser: GetUserUseCase,
    val saveUser: SaveUserUseCase,
    val clearUser: ClearUserUseCase
)