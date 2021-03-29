package br.com.fornaro.githubapis.data.source.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindEmojiRemoteDataSource(impl: EmojiRetrofitRemoteDataSource): EmojiRemoteDataSource

    @Binds
    abstract fun bindUserRemoteDataSource(impl: UserRetrofitRemoteDataSource): UserRemoteDataSource
}