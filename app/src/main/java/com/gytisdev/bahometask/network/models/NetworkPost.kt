package com.gytisdev.bahometask.network.models

import com.gytisdev.bahometask.application.storage.DatabasePost
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPost(
    val id: Int,
    val title: String
)

fun List<NetworkPost>.toDatabaseModel() = map {
    DatabasePost(
        id = it.id,
        title = it.title
    )
}
