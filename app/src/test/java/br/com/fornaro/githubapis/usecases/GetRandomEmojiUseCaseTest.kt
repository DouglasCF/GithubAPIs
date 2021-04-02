package br.com.fornaro.githubapis.usecases

import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.domain.usecases.GetRandomEmojiUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRandomEmojiUseCaseTest {

    private lateinit var useCase: GetRandomEmojiUseCase

    @MockK
    private lateinit var emojiRepository: EmojiRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = GetRandomEmojiUseCase(
            emojiRepository = emojiRepository,
        )
    }

    @Test
    fun `should get random emoji`() = runBlockingTest {
        val fetch = listOf<Emoji>(mockk(), mockk())
        coEvery { emojiRepository.fetchAll() } returns fetch

        val result = useCase()

        assert(result in fetch)
    }
}