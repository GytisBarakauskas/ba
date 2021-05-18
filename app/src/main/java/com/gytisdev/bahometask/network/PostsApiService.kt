package com.gytisdev.bahometask.network

import com.gytisdev.bahometask.network.model.NetworkPost
import com.gytisdev.bahometask.network.model.NetworkPostDetails
import com.gytisdev.bahometask.network.model.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): List<NetworkPost>

    @GET("posts/{id}")
    suspend fun getPostDetails(@Path("id") id: Int): NetworkPostDetails

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): NetworkUser
}