package br.com.fornaro.githubapis.data.source.remote.mappers

import br.com.fornaro.githubapis.data.source.remote.response.UserResponse
import br.com.fornaro.githubapis.domain.models.User

typealias UserMapperAlias = Mapper<UserResponse, User>

object UserMapper : UserMapperAlias {
    override fun map(input: UserResponse) = with(input) {
        User(
            login = login,
            imageUrl = avatarUrl
        )
    }
}