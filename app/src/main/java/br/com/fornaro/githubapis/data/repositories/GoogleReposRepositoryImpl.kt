package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.dispatchers.DispatcherMap
import br.com.fornaro.githubapis.data.source.remote.RepoRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.GoogleReposRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val USERNAME = "google"

class GoogleReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: RepoRemoteDataSource,
    private val dispatcherMap: DispatcherMap
) : GoogleReposRepository {
    override suspend fun fetch() = withContext(dispatcherMap.io) {
        remoteDataSource.fetch(USERNAME, 1, 20)
    }
}