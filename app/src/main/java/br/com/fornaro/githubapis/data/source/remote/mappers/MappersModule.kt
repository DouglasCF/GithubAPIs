package br.com.fornaro.githubapis.data.source.remote.mappers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideEmojiMapper(): EmojiMapperAlias = EmojiMapper

    @Provides
    fun provideUserMapper(): UserMapperAlias = UserMapper

    @Provides
    fun provideRepoMapper(): RepoMapperAlias = RepoMapper
}