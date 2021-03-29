package br.com.fornaro.githubapis.data.source.local.database.mappers

import br.com.fornaro.githubapis.data.source.local.database.entities.UserEntity
import br.com.fornaro.githubapis.domain.models.User

typealias UserEntityMapperAlias = EntityMapper<UserEntity, User>

object UserEntityMapper : UserEntityMapperAlias {
    override fun fromDomain(domain: User) = with(domain) {
        UserEntity(
            login = login,
            imageUrl = imageUrl
        )
    }

    override fun fromEntity(entity: UserEntity) = with(entity) {
        User(
            login = login,
            imageUrl = imageUrl
        )
    }
}