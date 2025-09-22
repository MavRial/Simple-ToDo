package com.example.simple_todo.data.mapper

import com.example.simple_todo.data.local.UserEntity
import com.example.simple_todo.data.remote.UserDto
import com.example.simple_todo.domain.model.User

//Dto to Domain
fun UserDto.toDomain() = User(
    uid = uid ?: "",
    email = email ?: "",
    displayName = displayName
)

// Domain to Dto
fun User.toDto(): UserDto =
    UserDto(
        uid = uid,
        email = email,
        displayName = displayName
    )

//Entity to Domain
fun UserEntity.toDomain() = User(
    uid = uid,
    email = email,
    displayName = displayName)

//Domain to Entity
fun User.toEntity(): UserEntity =
    UserEntity(
        uid = uid,
        email = email,
        displayName = displayName
    )