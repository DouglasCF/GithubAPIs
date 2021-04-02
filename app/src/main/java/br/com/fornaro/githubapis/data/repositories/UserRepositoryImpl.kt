package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.dispatchers.DispatcherMap
import br.com.fornaro.githubapis.data.source.local.UserLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.UserRemoteDataSource
import br.com.fornaro.githubapis.domain.models.User
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val dispatcher: DispatcherMap
) : UserRepository {

    override val users: Flow<List<User>>
        get() = localDataSource.users

    override suspend fun fetch(username: String) = withContext(dispatcher.io) {
        localDataSource.fetch(username)
            ?: remoteDataSource.fetch(username).also { localDataSource.insert(it) }
    }

    override suspend fun delete(user: User) = withContext(Dispatchers.IO) {
        localDataSource.delete(user)
    }
}