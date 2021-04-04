package br.com.fornaro.githubapis.data.source.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindEmojiLocalDataSource(impl: EmojiRoomLocalDataSource): EmojiLocalDataSource

    @Binds
    abstract fun bindUserLocalDataSource(impl: UserRoomLocalDataSource): UserLocalDataSource
}