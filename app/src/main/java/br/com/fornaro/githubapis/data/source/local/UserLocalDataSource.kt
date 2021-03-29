package br.com.fornaro.githubapis.data.source.local

import br.com.fornaro.githubapis.domain.models.User

interface UserLocalDataSource {

    suspend fun fetch(username: String): User?
    suspend fun insert(user: User)
}