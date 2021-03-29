package br.com.fornaro.githubapis.data.source.local

import br.com.fornaro.githubapis.data.source.local.database.daos.UserDao
import br.com.fornaro.githubapis.data.source.local.database.mappers.UserEntityMapperAlias
import br.com.fornaro.githubapis.domain.models.User
import javax.inject.Inject

class UserRoomLocalDataSource @Inject constructor(
    private val dao: UserDao,
    private val mapper: UserEntityMapperAlias
) : UserLocalDataSource {

    override suspend fun fetch(username: String) =
        dao.select(username)
            ?.let(mapper::fromEntity)

    override suspend fun insert(user: User) {
        mapper.fromDomain(user).also { dao.insert(it) }
    }
}