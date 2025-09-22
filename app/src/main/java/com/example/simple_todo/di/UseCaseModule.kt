package com.example.simple_todo.di

import com.example.simple_todo.domain.repository.AuthRepository
import com.example.simple_todo.domain.repository.TaskRepository
import com.example.simple_todo.domain.repository.UserRepository
import com.example.simple_todo.domain.usecase.AddTaskUseCase
import com.example.simple_todo.domain.usecase.AuthUseCases
import com.example.simple_todo.domain.usecase.ClearUserUseCase
import com.example.simple_todo.domain.usecase.DeleteTaskUseCase
import com.example.simple_todo.domain.usecase.GetCurrentUserUseCase
import com.example.simple_todo.domain.usecase.GetTasksUseCase
import com.example.simple_todo.domain.usecase.GetUserUseCase
import com.example.simple_todo.domain.usecase.LoginUseCase
import com.example.simple_todo.domain.usecase.LogoutUseCase
import com.example.simple_todo.domain.usecase.RegisterUseCase
import com.example.simple_todo.domain.usecase.SaveUserUseCase
import com.example.simple_todo.domain.usecase.SyncPendingTasksUseCase
import com.example.simple_todo.domain.usecase.TaskUseCases
import com.example.simple_todo.domain.usecase.UpdateTaskUseCase
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
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        login = LoginUseCase(repository),
        register = RegisterUseCase(repository),
        logout = LogoutUseCase(repository),
        getCurrentUser = GetCurrentUserUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUser = GetUserUseCase(repository),
        saveUser = SaveUserUseCase(repository),
        clearUser = ClearUserUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases = TaskUseCases(
        getTasks = GetTasksUseCase(repository),
        addTask = AddTaskUseCase(repository),
        updateTask = UpdateTaskUseCase(repository),
        deleteTask = DeleteTaskUseCase(repository),
        syncPending = SyncPendingTasksUseCase(repository)
    )
}