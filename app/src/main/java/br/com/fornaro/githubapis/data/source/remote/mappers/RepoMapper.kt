package br.com.fornaro.githubapis.data.source.remote.mappers

import br.com.fornaro.githubapis.data.source.remote.response.RepoResponse
import br.com.fornaro.githubapis.domain.models.Repo

typealias RepoMapperAlias = Mapper<RepoResponse, Repo>

object RepoMapper : RepoMapperAlias {
    override fun map(input: RepoResponse) = with(input) {
        Repo(
            fullName = fullName
        )
    }
}