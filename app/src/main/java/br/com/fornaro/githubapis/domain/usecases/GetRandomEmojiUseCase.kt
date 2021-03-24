package br.com.fornaro.githubapis.domain.usecases

import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import javax.inject.Inject

class GetRandomEmojiUseCase @Inject constructor(
    private val emojiRepository: EmojiRepository
) {

    suspend operator fun invoke() = emojiRepository.fetchAll().random()
}