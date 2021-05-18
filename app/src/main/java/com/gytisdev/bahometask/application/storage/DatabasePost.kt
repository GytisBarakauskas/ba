package com.gytisdev.bahometask.application.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gytisdev.bahometask.posts.data.model.Post

@Entity(tableName = "posts")
data class DatabasePost(
    @PrimaryKey
    val id: Int,
    val title: String
)

fun List<DatabasePost>.toDomainModel() = map {
    Post(id = it.id, title = it.title)
}