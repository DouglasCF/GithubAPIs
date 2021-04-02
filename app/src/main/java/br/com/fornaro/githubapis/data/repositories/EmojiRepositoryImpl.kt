package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.dispatchers.DispatcherMap
import br.com.fornaro.githubapis.data.source.local.EmojiLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.EmojiRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmojiRepositoryImpl @Inject constructor(
    private val remoteDataSource: EmojiRemoteDataSource,
    private val localDataSource: EmojiLocalDataSource,
    private val dispatcher: DispatcherMap
) : EmojiRepository {

    override suspend fun fetchAll() = withContext(dispatcher.io) {
        val result = localDataSource.fetchAll()
        if (result.isNullOrEmpty()) remoteDataSource.fetchAll().also { localDataSource.insert(it) }
        else result
    }
}