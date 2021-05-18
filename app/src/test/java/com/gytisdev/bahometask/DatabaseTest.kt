package com.gytisdev.bahometask

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gytisdev.bahometask.application.data.AppDatabase
import com.gytisdev.bahometask.application.data.PostsDao
import com.gytisdev.bahometask.application.data.UsersDao
import com.gytisdev.bahometask.application.storage.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@Config(sdk = [29])
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var usersDao: UsersDao
    private lateinit var postsDao: PostsDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        usersDao = db.usersDao()
        postsDao = db.postsDao()
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

    @Test
    fun when_InsertPosts_Expect_CountToMatch() = runBlocking {
        val posts = listOf(
            DatabasePost(22, "Post"),
            DatabasePost(44, "Post")
        )

        postsDao.insertPosts(posts)

        val usersInDb = postsDao.getAll().first()

        assert(usersInDb.count() == posts.count())
    }

    @Test
    fun when_InsertPostsThenRemoved_Expect_CountToBeZero() = runBlocking {
        val posts = listOf(
            DatabasePost(22, "Post"),
            DatabasePost(22, "Post")
        )

        postsDao.insertPosts(posts)
        postsDao.deleteAllPosts()

        val postsInDb = postsDao.getAll().first()

        assert(postsInDb.count() == 0)
    }
}