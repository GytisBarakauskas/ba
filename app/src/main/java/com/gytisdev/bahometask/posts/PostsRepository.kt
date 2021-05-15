package com.gytisdev.bahometask.posts

import com.gytisdev.bahometask.application.storage.realm
import com.gytisdev.bahometask.network.PostsApiService
import com.gytisdev.bahometask.posts.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val apiService: PostsApiService
) {

    suspend fun getPosts() = withContext(Dispatchers.IO) {

    }
}