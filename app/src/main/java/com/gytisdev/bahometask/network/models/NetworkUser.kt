package com.gytisdev.bahometask.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    val id: Int,
    val name: String
)
