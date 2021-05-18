package com.gytisdev.bahometask.application.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gytisdev.bahometask.application.storage.DatabasePostDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDetailsDao {
    @Query("SELECT * FROM postDetails WHERE id = :id")
    fun get(id: Int): Flow<DatabasePostDetails?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postDetails: DatabasePostDetails)

    @Query("DELETE FROM postDetails WHERE id = :id")
    suspend fun delete(id: Int)
}