package br.com.fornaro.githubapis.repositories

import br.com.fornaro.githubapis.data.repositories.EmojiRepositoryImpl
import br.com.fornaro.githubapis.data.source.local.EmojiLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.EmojiRemoteDataSource
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.tools.UnitTestDispatcherMap
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class EmojiRepositoryTest {

    private lateinit var repository: EmojiRepository

    @RelaxedMockK
    private lateinit var remoteDataSource: EmojiRemoteDataSource

    @RelaxedMockK
    private lateinit var localDataSource: EmojiLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = EmojiRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatcher = UnitTestDispatcherMap
        )
    }

    @Test
    fun `should fetch remotely when local is empty`() = runBlockingTest {
        coEvery { localDataSource.fetchAll() } returns emptyList()
        coEvery { remoteDataSource.fetchAll() } returns listOf(mockk(), mockk())

        repository.fetchAll()

        coVerify { remoteDataSource.fetchAll() }
    }

    @Test
    fun `should insert locally when fetching remotely`() = runBlockingTest {
        val fetch = listOf<Emoji>(mockk(), mockk())
        coEvery { localDataSource.fetchAll() } returns emptyList()
        coEvery { remoteDataSource.fetchAll() } returns fetch

        repository.fetchAll()

        coVerify { localDataSource.insert(fetch) }
    }

    @Test
    fun `should not fetch remotely when there is data locally`() = runBlockingTest {
        val fetch = listOf<Emoji>(mockk())
        coEvery { localDataSource.fetchAll() } returns fetch

        val result = repository.fetchAll()

        assertEquals(fetch, result)
        coVerify(exactly = 0) { remoteDataSource.fetchAll() }
    }
}