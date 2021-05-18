package com.gytisdev.bahometask

import com.gytisdev.bahometask.application.networkBoundResource
import com.gytisdev.bahometask.application.storage.DatabaseUser
import com.gytisdev.bahometask.network.models.NetworkUser
import com.gytisdev.bahometask.postdetails.data.User
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NetworkBoundResourceTest {

    interface DummyNetworkBoundResourceFunctionHolder {
        fun getQuery(): Flow<DatabaseUser>
        suspend fun fetch(): NetworkUser
        fun transform(user: DatabaseUser): User
        fun saveFetchResult(user: NetworkUser)
        fun shouldFetch(): Boolean
    }

    @MockK
    lateinit var holder: DummyNetworkBoundResourceFunctionHolder

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { holder.getQuery() } returns flow { emit(DatabaseUser(4, "Seras")) }
        coEvery { holder.fetch() } returns NetworkUser(4, "Seras")
        every { holder.transform(any()) } returns User(4, "Seras")
        every { holder.saveFetchResult(any()) } just runs
    }

    @Test
    fun `query result is transformed on return`() = runBlocking {
        every { holder.shouldFetch() } returns true
        val flow = networkBoundResource(
            query = {
                holder.getQuery()
            },
            transform = {
                holder.transform(it)
            },
            fetch = {
                holder.fetch()
            },
            saveFetchResult = {
                holder.saveFetchResult(it)
            },
            shouldFetch = {
                holder.shouldFetch()
            }
        )
        val expectedResult = User(4, "Seras")

        val result = flow.take(7).toList().first().data

        assert(expectedResult == result)
    }

    @Test
    fun `resource calls correct sequence when fetching`() = runBlocking {
        every { holder.shouldFetch() } returns true
        val flow = networkBoundResource(
            query = {
                holder.getQuery()
            },
            transform = {
                holder.transform(it)
            },
            fetch = {
                holder.fetch()
            },
            saveFetchResult = {
                holder.saveFetchResult(it)
            },
            shouldFetch = {
                holder.shouldFetch()
            }
        )

        flow.take(7).toList()

        coVerifySequence {
            holder.getQuery()
            holder.shouldFetch()
            holder.transform(any())
            holder.fetch()
            holder.saveFetchResult(any())
            holder.getQuery()
            holder.transform(any())
        }
    }

    @Test
    fun `resource calls correct sequence when not fetching`() = runBlocking {
        every { holder.shouldFetch() } returns false
        val flow = networkBoundResource(
            query = {
                holder.getQuery()
            },
            transform = {
                holder.transform(it)
            },
            fetch = {
                holder.fetch()
            },
            saveFetchResult = {
                holder.saveFetchResult(it)
            },
            shouldFetch = {
                holder.shouldFetch()
            }
        )

        flow.first()

        coVerifySequence {
            holder.getQuery()
            holder.shouldFetch()
            holder.getQuery()
            holder.transform(any())
        }
    }
}