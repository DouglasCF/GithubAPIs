package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.domain.repositories.GoogleReposRepository
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindEmojiRepository(impl: EmojiRepositoryImpl): EmojiRepository

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindGoogleReposRepository(impl: GoogleReposRepositoryImpl): GoogleReposRepository
}