package br.com.fornaro.githubapis.data.source.local.database.mappers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EntityMappersModule {

    @Provides
    fun provideEntityEmojiMapper(): EmojiEntityMapperAlias = EmojiEntityMapper
}