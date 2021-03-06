package com.gytisdev.bahometask.application.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gytisdev.bahometask.application.storage.DatabasePost
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {
    @Query("SELECT * FROM posts")
    fun getAll(): Flow<List<DatabasePost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<DatabasePost>)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()
}