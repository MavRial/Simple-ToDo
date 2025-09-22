package com.example.simple_todo.data.repository

import com.example.simple_todo.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            Result.success(result.user?.uid ?: "")
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            Result.success(result.user?.uid ?: "")
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUid(): Result<String?> {
        return try {
            val uid = auth.currentUser?.uid
            if (uid != null){
                Result.success(uid)
            }else {
                Result.failure(IllegalStateException("No user logged in"))
            }
        } catch (e:Exception){
            Result.failure(e)
        }
    }

}