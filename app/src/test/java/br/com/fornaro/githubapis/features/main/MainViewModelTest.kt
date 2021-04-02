package br.com.fornaro.githubapis.features.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import br.com.fornaro.githubapis.domain.error.USER_NOT_FOUND_ERROR_MESSAGE
import br.com.fornaro.githubapis.domain.event.MessageEvent
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import br.com.fornaro.githubapis.domain.usecases.GetRandomEmojiUseCase
import br.com.fornaro.githubapis.tools.BaseCoroutinesTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MainViewModelTest : BaseCoroutinesTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var getRandomEmojiUseCase: GetRandomEmojiUseCase

    @MockK
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = MainViewModel(
            getRandomEmojiUseCase = getRandomEmojiUseCase,
            userRepository = userRepository,
        )
    }

    @Test
    fun `should search username successfully`() = runBlockingTest {
        coEvery { userRepository.fetch(any()) } returns mockk()

        viewModel.message.test {

            viewModel.searchUsername("username")

            assertEquals(MessageEvent.USER_FOUND, expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should show user not found message`() = runBlockingTest {
        coEvery { userRepository.fetch(any()) } throws mockk<HttpException> {
            every { response()?.errorBody()?.string() } returns USER_NOT_FOUND_ERROR_MESSAGE
        }

        viewModel.message.test {
            viewModel.searchUsername("username")

            assertEquals(MessageEvent.USER_NOT_FOUND, expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should get random emoji successfully`() = runBlockingTest {
        val emoji = mockk<Emoji>()
        coEvery { getRandomEmojiUseCase() } returns emoji

        viewModel.state.test {
            expectItem()
            viewModel.getRandomEmoji()

            assertEquals(MainState(isLoadingEmojis = true, emoji = null), expectItem())
            assertEquals(MainState(isLoadingEmojis = false, emoji = emoji), expectItem())
        }
    }
}