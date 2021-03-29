package br.com.fornaro.githubapis.domain.repositories

import br.com.fornaro.githubapis.domain.models.User

interface UserRepository {

    suspend fun fetch(username: String): User
}