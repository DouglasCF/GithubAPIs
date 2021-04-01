package br.com.fornaro.githubapis.domain.repositories

import br.com.fornaro.githubapis.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val users: Flow<List<User>>

    suspend fun fetch(username: String): User
    suspend fun delete(user: User)
}