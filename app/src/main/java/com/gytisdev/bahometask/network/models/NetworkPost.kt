package com.gytisdev.bahometask.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPost(
    val id: Int,
    //val userId: Int,
    val title: String,
    val body: String
)
