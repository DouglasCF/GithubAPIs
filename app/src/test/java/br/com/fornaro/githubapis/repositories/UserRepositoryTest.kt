package br.com.fornaro.githubapis.repositories

import br.com.fornaro.githubapis.data.repositories.UserRepositoryImpl
import br.com.fornaro.githubapis.data.source.local.UserLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.UserRemoteDataSource
import br.com.fornaro.githubapis.domain.models.User
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import br.com.fornaro.githubapis.tools.UnitTestDispatcherMap
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var repository: UserRepository

    @RelaxedMockK
    private lateinit var remoteDataSource: UserRemoteDataSource

    @RelaxedMockK
    private lateinit var localDataSource: UserLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = UserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = UnitTestDispatcherMap
        )
    }

    @Test
    fun `should fetch remotely when local is null`() = runBlockingTest {
        coEvery { localDataSource.fetch(any()) } returns null
        coEvery { remoteDataSource.fetch(any()) } returns mockk()

        repository.fetch("username")

        coVerify(exactly = 1) { remoteDataSource.fetch(any()) }
    }

    @Test
    fun `should insert locally when fetching remotely`() = runBlockingTest {
        val user = mockk<User>()
        coEvery { localDataSource.fetch(any()) } returns null
        coEvery { remoteDataSource.fetch(any()) } returns user

        repository.fetch("username")

        coVerify { localDataSource.insert(user) }
    }

    @Test
    fun `should not fetch remotely when there is data locally`() = runBlockingTest {
        val user = mockk<User>()
        coEvery { localDataSource.fetch(any()) } returns user

        val result = repository.fetch("username")

        Assert.assertEquals(user, result)
        coVerify(exactly = 0) { remoteDataSource.fetch(any()) }
    }
}