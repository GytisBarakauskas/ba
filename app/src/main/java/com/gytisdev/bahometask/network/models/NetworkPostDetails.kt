package com.gytisdev.bahometask.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPostDetails (
    val userID: Int,
    val body: String
)