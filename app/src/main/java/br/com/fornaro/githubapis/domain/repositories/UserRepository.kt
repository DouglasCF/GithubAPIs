package br.com.fornaro.githubapis.domain.repositories

import br.com.fornaro.githubapis.domain.models.User

interface UserRepository {

    suspend fun fetchAll(): List<User>
    suspend fun fetch(username: String): User
    suspend fun delete(user: User)
}