package br.com.fornaro.githubapis.features.emojis

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import br.com.fornaro.githubapis.domain.event.MessageEvent
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.tools.BaseCoroutinesTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class EmojisViewModelTest : BaseCoroutinesTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EmojisViewModel

    @MockK
    private lateinit var emojiRepository: EmojiRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = EmojisViewModel(
            emojiRepository = emojiRepository,
        )
    }

    @Test
    fun `should load emojis successfully`() = runBlockingTest {
        val emojis = mockk<List<Emoji>>()
        coEvery { emojiRepository.fetchAll() } returns emojis
        viewModel.state.test {
            viewModel.loadEmojis()
            assertEquals(EmojisState.Loading, expectItem())
            assertEquals(EmojisState.Content(emojis = emojis), expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should show generic error message when loading emojis`() = runBlockingTest {
        coEvery { emojiRepository.fetchAll() } throws Exception()
        viewModel.message.test {
            viewModel.loadEmojis()
            assertEquals(MessageEvent.GENERIC_MESSAGE, expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should remove emoji from content successfully`() = runBlockingTest {
        val emoji1 = mockk<Emoji>()
        val emoji2 = mockk<Emoji>()

        viewModel.state.value = EmojisState.Content(emojis =  listOf(emoji1, emoji2))

        viewModel.state.test {
            expectItem()
            viewModel.removeEmoji(emoji1)
            assertEquals(EmojisState.Content(emojis = listOf(emoji2)), expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}