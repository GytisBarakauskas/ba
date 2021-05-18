package com.gytisdev.bahometask

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gytisdev.bahometask.application.storage.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var postsDao: PostsDao
    private lateinit var usersDao: UsersDao
    private lateinit var postDetailsDao: PostDetailsDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        postsDao = db.postsDao()
        usersDao = db.usersDao()
        postDetailsDao = db.postDetailsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun when_InsertUser_Expect_ItToMatch() = runBlocking {
        val user = DatabaseUser(1, "Bajor", 123L)

        usersDao.insert(user)

        val userInDb = usersDao.get(user.id).first()

        assert(userInDb == user)
    }
}