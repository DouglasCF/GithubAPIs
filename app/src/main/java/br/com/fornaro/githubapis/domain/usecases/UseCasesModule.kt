package br.com.fornaro.githubapis.domain.usecases

import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetEmojiUseCase(emojiRepository: EmojiRepository) =
        GetRandomEmojiUseCase(emojiRepository)
}