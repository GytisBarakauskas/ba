package com.gytisdev.bahometask.network.models

import com.gytisdev.bahometask.application.storage.DatabaseUser
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    val id: Int,
    val name: String
)

fun NetworkUser.toDatabaseModel() = DatabaseUser(
    id, name
)
