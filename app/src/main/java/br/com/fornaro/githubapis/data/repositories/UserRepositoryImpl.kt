package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.source.local.UserLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.UserRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun fetch(username: String) = withContext(Dispatchers.IO) {
        localDataSource.fetch(username)
            ?: remoteDataSource.fetch(username).also { localDataSource.insert(it) }
    }
}