package br.com.fornaro.githubapis.data.source.local

import br.com.fornaro.githubapis.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    val users: Flow<List<User>>

    suspend fun fetch(username: String): User?
    suspend fun insert(user: User)
    suspend fun delete(user: User)
}