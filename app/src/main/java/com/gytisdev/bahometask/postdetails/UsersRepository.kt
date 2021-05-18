package com.gytisdev.bahometask.postdetails

import com.gytisdev.bahometask.application.networkBoundResource
import com.gytisdev.bahometask.application.storage.UsersDao
import com.gytisdev.bahometask.application.storage.toDomainModel
import com.gytisdev.bahometask.network.PostsApiService
import com.gytisdev.bahometask.network.models.toDatabaseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val postsApiService: PostsApiService,
    private val usersDao: UsersDao
) {
    fun getUser(id: Int) = networkBoundResource(
        query = {
            usersDao.get(id)
        },
        transform = {
            it?.toDomainModel()
        },
        fetch = {
            postsApiService.getUser(id)
        },
        saveFetchResult = {
            val databaseModel = it.toDatabaseModel()
            databaseModel.updatedAt = System.currentTimeMillis()
            usersDao.insert(databaseModel)
        },
        shouldFetch = shouldFetch@{
            it?.let {
                return@shouldFetch System.currentTimeMillis() > it.updatedAt + syncPeriod
            }
            true
        }
    )

    private companion object {
        const val syncPeriod = 1000 * 60 * 5 // 5 minutes
    }
}