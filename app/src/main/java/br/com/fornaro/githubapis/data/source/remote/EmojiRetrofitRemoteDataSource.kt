package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.data.source.remote.api.GithubApi
import br.com.fornaro.githubapis.data.source.remote.mappers.EmojiMapperAlias
import br.com.fornaro.githubapis.domain.models.Emoji
import javax.inject.Inject

class EmojiRetrofitRemoteDataSource @Inject constructor(
    private val api: GithubApi,
    private val mapper: EmojiMapperAlias
) : EmojiRemoteDataSource {
    override suspend fun fetchAll(): List<Emoji> {
        val response = api.fetchEmojis()
        return response.map(mapper::map)
    }
}