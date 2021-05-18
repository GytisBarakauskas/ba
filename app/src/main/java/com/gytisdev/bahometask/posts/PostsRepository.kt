package com.gytisdev.bahometask.posts

import com.gytisdev.bahometask.application.networkBoundResource
import com.gytisdev.bahometask.application.preferences.SyncPreferences
import com.gytisdev.bahometask.application.storage.PostsDao
import com.gytisdev.bahometask.application.storage.toDomainModel
import com.gytisdev.bahometask.network.PostsApiService
import com.gytisdev.bahometask.network.models.toDatabaseModel
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsApiService: PostsApiService,
    private val postsDao: PostsDao,
    private val syncPreferences: SyncPreferences
) {

    fun getPosts(forceRefresh: Boolean = false) = networkBoundResource(
        query = {
            postsDao.getAll()
        },
        transform = {
            it.toDomainModel()
        },
        fetch = {
            postsApiService.getPosts()
        },
        saveFetchResult = {
            val posts = it.toDatabaseModel()
            postsDao.deleteAllPosts()
            postsDao.insertPosts(posts)
            syncPreferences.lastPostsListSyncTimestamp = System.currentTimeMillis()
        },
        shouldFetch = {
            val lastSyncedAt = syncPreferences.lastPostsListSyncTimestamp
            forceRefresh || System.currentTimeMillis() > lastSyncedAt + syncPeriod
        }
    )

    private companion object {
        const val syncPeriod = 1000 * 60 // 1 minute
    }
}