package com.example.simple_todo.di

import com.example.simple_todo.data.local.UserDao
import com.example.simple_todo.data.repository.AuthRepositoryImpl
import com.example.simple_todo.data.repository.UserRepositoryImpl
import com.example.simple_todo.domain.repository.AuthRepository
import com.example.simple_todo.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(auth)


    @Provides
    @Singleton
    fun provideUserRepository(
        dao: UserDao,
        firestore: FirebaseFirestore
    ): UserRepository = UserRepositoryImpl(dao, firestore)

}