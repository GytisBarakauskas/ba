package com.gytisdev.bahometask.application.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DatabasePost::class, DatabasePostDetails::class, DatabaseUser::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun postDetailsDao(): PostDetailsDao
    abstract fun usersDao(): UsersDao
}