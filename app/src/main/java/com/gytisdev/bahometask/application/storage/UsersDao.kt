package com.gytisdev.bahometask.application.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun get(id: Int): Flow<DatabaseUser?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postDetails: DatabaseUser)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun delete(id: Int)
}