package br.com.fornaro.githubapis.data.source.remote

import br.com.fornaro.githubapis.domain.models.Repo

interface RepoRemoteDataSource {

    suspend fun fetch(username: String, page: Int, size: Int): List<Repo>
}