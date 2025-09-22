package com.example.simple_todo.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simple_todo.data.local.UserDao
import com.example.simple_todo.data.local.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao
}


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase =
        Room.databaseBuilder(app, AppDataBase::class.java, "simple_todo_db").build()

    @Provides
    fun provideUserDao(db: AppDataBase): UserDao = db.userDao()
}