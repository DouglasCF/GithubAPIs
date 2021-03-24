package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.domain.models.Emoji

interface EmojiRemoteDataSource {

    suspend fun fetchAll(): List<Emoji>
}