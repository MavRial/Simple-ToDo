package com.example.simple_todo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val pendingSync: Boolean = true
)