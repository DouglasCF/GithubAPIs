package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.source.local.EmojiLocalDataSource
import br.com.fornaro.githubapis.data.source.remote.EmojiRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmojiRepositoryImpl @Inject constructor(
    private val remoteDataSource: EmojiRemoteDataSource,
    private val localDataSource: EmojiLocalDataSource
) : EmojiRepository {

    override suspend fun fetchAll() = withContext(Dispatchers.IO) {
        val result = localDataSource.fetchAll()
        if (result.isNullOrEmpty()) remoteDataSource.fetchAll().also { localDataSource.insert(it) }
        else result
    }
}