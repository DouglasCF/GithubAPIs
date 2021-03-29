package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.source.remote.UserRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun fetch(username: String) = withContext(Dispatchers.IO) {
        remoteDataSource.fetch(username)
    }
}