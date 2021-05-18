package com.gytisdev.bahometask.application.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gytisdev.bahometask.application.storage.DatabasePost
import com.gytisdev.bahometask.application.storage.DatabasePostDetails
import com.gytisdev.bahometask.application.storage.DatabaseUser

@Database(
    entities = [DatabasePost::class, DatabasePostDetails::class, DatabaseUser::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun postDetailsDao(): PostDetailsDao
    abstract fun usersDao(): UsersDao
}