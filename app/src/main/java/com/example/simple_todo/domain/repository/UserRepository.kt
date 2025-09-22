package com.example.simple_todo.domain.repository

import com.example.simple_todo.domain.model.User

interface UserRepository {
    suspend fun getUser(): User?
    suspend fun saveUser(user: User)
    suspend fun clearUser()

}