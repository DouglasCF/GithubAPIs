package br.com.fornaro.githubapis.domain.repositories

import br.com.fornaro.githubapis.domain.models.Repo

interface GoogleReposRepository {

    suspend fun fetch(): List<Repo>
}