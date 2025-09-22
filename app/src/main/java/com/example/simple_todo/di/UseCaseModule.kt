package com.example.simple_todo.di

import com.example.simple_todo.domain.repository.AuthRepository
import com.example.simple_todo.domain.repository.UserRepository
import com.example.simple_todo.domain.usecase.AuthUseCases
import com.example.simple_todo.domain.usecase.ClearUserUseCase
import com.example.simple_todo.domain.usecase.GetCurrentUserUseCase
import com.example.simple_todo.domain.usecase.GetUserUseCase
import com.example.simple_todo.domain.usecase.LoginUseCase
import com.example.simple_todo.domain.usecase.LogoutUseCase
import com.example.simple_todo.domain.usecase.RegisterUseCase
import com.example.simple_todo.domain.usecase.SaveUserUseCase
import com.example.simple_todo.domain.usecase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCases(repo: AuthRepository) = AuthUseCases(
        login = LoginUseCase(repo),
        register = RegisterUseCase(repo),
        logout = LogoutUseCase(repo),
        getCurrentUser = GetCurrentUserUseCase(repo)
    )

    @Provides
    @Singleton
    fun provideUserUseCases(repo: UserRepository) = UserUseCases(
        getUser = GetUserUseCase(repo),
        saveUser = SaveUserUseCase(repo),
        clearUser = ClearUserUseCase(repo)
    )
}