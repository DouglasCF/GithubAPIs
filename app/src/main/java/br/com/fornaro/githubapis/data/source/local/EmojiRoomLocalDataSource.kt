package br.com.fornaro.githubapis.data.source.local

import br.com.fornaro.githubapis.data.source.local.database.daos.EmojiDao
import br.com.fornaro.githubapis.data.source.local.database.mappers.EmojiEntityMapperAlias
import br.com.fornaro.githubapis.domain.models.Emoji
import javax.inject.Inject

class EmojiRoomLocalDataSource @Inject constructor(
    private val dao: EmojiDao,
    private val mapper: EmojiEntityMapperAlias
) : EmojiLocalDataSource {
    override suspend fun fetchAll(): List<Emoji> {
        val result = dao.fetchAll()
        return result.map(mapper::fromEntity)
    }

    override suspend fun insert(emojis: List<Emoji>) {
        emojis.map(mapper::fromDomain).also { dao.insert(it) }
    }
}