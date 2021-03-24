package br.com.fornaro.githubapis.data.repositories

import br.com.fornaro.githubapis.data.source.remote.EmojiRemoteDataSource
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmojiRepositoryImpl @Inject constructor(
    private val remoteDataSource: EmojiRemoteDataSource
) : EmojiRepository {

    override suspend fun fetchAll() = withContext(Dispatchers.IO) {
        remoteDataSource.fetchAll()
    }
}