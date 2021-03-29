package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.domain.models.User

interface UserRemoteDataSource {

    suspend fun fetch(username: String): User
}