package com.gytisdev.bahometask.application.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gytisdev.bahometask.postdetails.data.model.PostDetails

@Entity(tableName = "postDetails")
data class DatabasePostDetails(
    @PrimaryKey
    val id: Int,
    val userID: Int,
    val title: String,
    val body: String,
    var updatedAt: Long = 0
)

fun DatabasePostDetails.toDomainModel() = PostDetails(title, body, userID)