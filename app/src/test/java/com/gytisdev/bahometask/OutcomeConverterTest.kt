package com.gytisdev.bahometask

import com.gytisdev.bahometask.application.common.ErrorHelper
import com.gytisdev.bahometask.application.common.Outcome
import com.gytisdev.bahometask.application.common.OutcomeConverter
import com.gytisdev.bahometask.application.common.TaskStatus
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.io.IOException

class OutcomeConverterTest {
    @MockK
    lateinit var errorHelper: ErrorHelper

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { errorHelper.getErrorMessage(any()) } returns "Error message"
    }

    @Test
    fun `when outcome is error task result is error with error message`() {
        val data: Boolean? = true
        val outcomeConverter = OutcomeConverter(errorHelper)
        val outcome = Outcome.Error(IOException(), data)

        val result = outcomeConverter.toTaskStatusWithResolvedError(outcome)

        assert(result is TaskStatus.Failure)
        assert((result as TaskStatus.Failure).errorMessage == "Error message")
        assert(result.data == data)

        verify { errorHelper.getErrorMessage(any()) }
    }

    @Test
    fun `when outcome is success task result is success`() {
        val data = true
        val outcomeConverter = OutcomeConverter(errorHelper)
        val outcome = Outcome.Success(data)

        val result = outcomeConverter.toTaskStatusWithResolvedError(outcome)

        assert(result is TaskStatus.Success)
        assert((result as TaskStatus.Success).result == data)
    }

    @Test
    fun `when outcome is loading task result is loading`() {
        val data: Boolean? = true
        val outcomeConverter = OutcomeConverter(errorHelper)
        val outcome = Outcome.Loading(data)

        val result = outcomeConverter.toTaskStatusWithResolvedError(outcome)

        assert(result is TaskStatus.Loading)
        assert((result as TaskStatus.Loading).result == data)
    }
}