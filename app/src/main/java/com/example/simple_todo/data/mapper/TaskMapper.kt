package com.example.simple_todo.data.mapper

import com.example.simple_todo.data.local.TaskEntity
import com.example.simple_todo.data.remote.TaskDto
import com.example.simple_todo.domain.model.Task

fun TaskEntity.toDomain() = Task(
    id,
    userId,
    title,
    description,
    isCompleted,
    pendingSync
)

fun Task.toEntity() = TaskEntity(
    id,
    userId,
    title,
    description,
    isCompleted,
    pendingSync
)

fun TaskDto.toDomain() = Task(
    id ?: "",
    userId ?: "",
    title ?: "",
    description ?: "",
    isCompleted == true,
    pendingSync != false
)
fun Task.toDto() = TaskDto(
    id,
    userId,
    title,
    description,
    isCompleted,
    pendingSync
)