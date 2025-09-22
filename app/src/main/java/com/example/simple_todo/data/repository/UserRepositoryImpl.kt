package com.example.simple_todo.data.repository

import com.example.simple_todo.data.local.UserDao
import com.example.simple_todo.data.mapper.toDomain
import com.example.simple_todo.data.mapper.toDto
import com.example.simple_todo.data.mapper.toEntity
import com.example.simple_todo.domain.model.User
import com.example.simple_todo.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val dao: UserDao,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getUser(): User? {
        return dao.getUser()?.toDomain()
    }

    override suspend fun saveUser(user: User) {
        dao.saveUser(user.toEntity())

        firestore.collection("user").document(user.uid).set(user.toDto()).await()
    }

    override suspend fun clearUser() {
        dao.clearUser()
    }
}