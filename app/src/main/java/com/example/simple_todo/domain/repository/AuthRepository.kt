package com.example.simple_todo.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun register(email: String, password: String): Result<String>
    suspend fun logout()
    fun getCurrentUid(): Result<String?>
}