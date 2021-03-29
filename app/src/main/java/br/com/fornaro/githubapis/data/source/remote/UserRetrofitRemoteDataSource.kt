package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.data.source.remote.api.GithubApi
import br.com.fornaro.githubapis.data.source.remote.mappers.UserMapperAlias
import br.com.fornaro.githubapis.domain.models.User
import javax.inject.Inject

class UserRetrofitRemoteDataSource @Inject constructor(
    private val api: GithubApi,
    private val mapper:UserMapperAlias
) : UserRemoteDataSource {
    override suspend fun fetch(username: String): User {
        val response = api.fetchUser(username)
        return mapper.map(response)
    }
}