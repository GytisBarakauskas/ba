package com.gytisdev.bahometask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gytisdev.bahometask.application.common.Outcome
import com.gytisdev.bahometask.application.common.OutcomeConverter
import com.gytisdev.bahometask.application.common.TaskStatus
import com.gytisdev.bahometask.postdetails.PostDetailsRepository
import com.gytisdev.bahometask.postdetails.PostDetailsViewModel
import com.gytisdev.bahometask.postdetails.UsersRepository
import com.gytisdev.bahometask.postdetails.data.model.User
import com.gytisdev.bahometask.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [29])
@RunWith(AndroidJUnit4::class)
class PostDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var usersRepository: UsersRepository

    @MockK
    lateinit var postDetailsRepository: PostDetailsRepository

    @MockK
    lateinit var outcomeConverter: OutcomeConverter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `repo success live data fills with user`() = runBlocking {
        val user = User(1, "La")
        every { outcomeConverter.toTaskStatusWithResolvedError(any<Outcome<User>>()) } returns TaskStatus.success(user)
        every { usersRepository.getUser(any()) } returns flowOf(Outcome.Success(user))

        val postDetailsViewModel = PostDetailsViewModel(postDetailsRepository, usersRepository, outcomeConverter)
        postDetailsViewModel.getUser(1)

        val userTaskStatus = postDetailsViewModel.user.getOrAwaitValue()

        assert(userTaskStatus is TaskStatus.Success)
        assert((userTaskStatus as TaskStatus.Success).result == user)
    }
}