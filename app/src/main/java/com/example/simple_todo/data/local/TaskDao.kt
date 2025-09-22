package com.example.simple_todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE userId = :userId")
    fun getTasksForUserFlow(userId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE userId = :userId AND isCompleted = :completed")
    fun getTasksForUserFilteredFlow(userId: String, completed: Boolean): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE pendingSync = 1")
    suspend fun getPendingSyncTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)
}