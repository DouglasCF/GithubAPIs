package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.data.source.remote.api.GithubApi
import br.com.fornaro.githubapis.data.source.remote.mappers.RepoMapperAlias
import br.com.fornaro.githubapis.domain.models.Repo
import javax.inject.Inject

class RepoRetrofitRemoteDataSource @Inject constructor(
    private val api: GithubApi,
    private val mapper: RepoMapperAlias
) : RepoRemoteDataSource {
    override suspend fun fetch(username: String, page: Int, size: Int): List<Repo> {
        val response = api.fetchRepos(username, page, size)
        return response.map(mapper::map)
    }
}