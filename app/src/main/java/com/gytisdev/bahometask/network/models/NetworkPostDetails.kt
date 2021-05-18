package com.gytisdev.bahometask.network.models

import com.gytisdev.bahometask.application.storage.DatabasePostDetails
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPostDetails(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun NetworkPostDetails.toDatabaseModel() = DatabasePostDetails(
    id, userId, title, body
)