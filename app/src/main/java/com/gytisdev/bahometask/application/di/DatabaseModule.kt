package com.gytisdev.bahometask.application.di

import android.content.Context
import androidx.room.Room
import com.gytisdev.bahometask.application.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "posts_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Reusable
    fun providePostsDao(database: AppDatabase) = database.postsDao()

    @Provides
    @Reusable
    fun providePostDetailsDao(database: AppDatabase) = database.postDetailsDao()

    @Provides
    @Reusable
    fun provideUsersDao(database: AppDatabase) = database.usersDao()
}