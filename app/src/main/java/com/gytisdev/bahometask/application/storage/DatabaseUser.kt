package com.gytisdev.bahometask.application.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gytisdev.bahometask.postdetails.data.model.User

@Entity(tableName = "users")
data class DatabaseUser(
    @PrimaryKey
    val id: Int,
    val name: String,
    var updatedAt: Long = 0
)

fun DatabaseUser.toDomainModel() = User(id, name)