package com.gytisdev.bahometask.postdetails

import com.gytisdev.bahometask.application.networkBoundResource
import com.gytisdev.bahometask.application.data.PostDetailsDao
import com.gytisdev.bahometask.application.storage.toDomainModel
import com.gytisdev.bahometask.network.PostsApiService
import com.gytisdev.bahometask.network.model.toDatabaseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDetailsRepository @Inject constructor(
    private val postsApiService: PostsApiService,
    private val postDetailsDao: PostDetailsDao
) {

    fun getPostDetails(id: Int) = networkBoundResource(
        query = {
            postDetailsDao.get(id)
        },
        transform = {
            it?.toDomainModel()
        },
        fetch = {
            postsApiService.getPostDetails(id)
        },
        saveFetchResult = {
            val databaseModel = it.toDatabaseModel()
            databaseModel.updatedAt = System.currentTimeMillis()
            postDetailsDao.insert(databaseModel)
        },
        shouldFetch = shouldFetch@{
            it?.let {
                return@shouldFetch System.currentTimeMillis() > it.updatedAt + syncPeriod
            }
            true
        }
    )

    private companion object {
        const val syncPeriod = 1000 * 60 // 1 minute
    }
}