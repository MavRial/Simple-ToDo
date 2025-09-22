package com.example.simple_todo.data.repository

import com.example.simple_todo.data.local.TaskDao
import com.example.simple_todo.data.local.TaskEntity
import com.example.simple_todo.data.mapper.toDomain
import com.example.simple_todo.data.mapper.toDto
import com.example.simple_todo.data.mapper.toEntity
import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.domain.repository.AuthRepository
import com.example.simple_todo.domain.repository.TaskFilter
import com.example.simple_todo.domain.repository.TaskRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class TaskRepositoryImpl(
    private val dao: TaskDao,
    private val auth: AuthRepository,
    private val firestore: FirebaseFirestore
) : TaskRepository {

    override fun getTasksFlow(filter: TaskFilter): Flow<List<Task>> = flow {
        val uid = auth.getCurrentUid().getOrNull() ?: return@flow
        val taskFlow: Flow<List<TaskEntity>> = when (filter) {
            TaskFilter.All -> dao.getTasksForUserFlow(uid)
            TaskFilter.Completed -> dao.getTasksForUserFilteredFlow(uid, true)
            TaskFilter.Incomplete -> dao.getTasksForUserFilteredFlow(uid, false)
        }
        emitAll(taskFlow.map { list -> list.map { it.toDomain() } })
    }

    override suspend fun addTask(task: Task) {
        val uid = auth.getCurrentUid().getOrNull() ?: return
        dao.insertTask(task.copy(userId = uid, pendingSync = true).toEntity())
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task.copy(pendingSync = true).toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toEntity())
    }

    override suspend fun syncPendingTasks() {
        val pending = dao.getPendingSyncTasks()
        pending.forEach { entity ->
            try {
                val dto = entity.toDomain().toDto()
                firestore.collection("tasks")
                    .document(entity.id)
                    .set(dto)
                    .await()

                dao.updateTask(entity.copy(pendingSync = false))
            } catch (_: Exception) {

            }
        }
    }
}