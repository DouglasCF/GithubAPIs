package br.com.fornaro.githubapis.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.fornaro.githubapis.data.source.paging.GithubRepoPagingSource
import br.com.fornaro.githubapis.data.source.remote.api.GithubApi
import br.com.fornaro.githubapis.data.source.remote.mappers.RepoMapperAlias
import br.com.fornaro.githubapis.domain.repositories.GoogleReposRepository
import javax.inject.Inject

private const val USERNAME = "google"

class GoogleReposRepositoryImpl @Inject constructor(
    private val api: GithubApi,
    private val mapperAlias: RepoMapperAlias
) : GoogleReposRepository {
    override fun fetch() = Pager(
        pagingSourceFactory = { GithubRepoPagingSource(api, mapperAlias, USERNAME) },
        config = PagingConfig(
            pageSize = 20
        )
    ).flow
}