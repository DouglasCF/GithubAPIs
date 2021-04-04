package br.com.fornaro.githubapis.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.fornaro.githubapis.data.source.remote.api.GithubApi
import br.com.fornaro.githubapis.data.source.remote.mappers.RepoMapperAlias
import br.com.fornaro.githubapis.domain.models.Repo

private const val INITIAL_PAGE = 1

class GithubRepoPagingSource(
    private val api: GithubApi,
    private val mapper: RepoMapperAlias,
    private val username: String
) : PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.fetchRepos(username, page, params.loadSize)
            LoadResult.Page(
                data = response.map(mapper::map),
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}