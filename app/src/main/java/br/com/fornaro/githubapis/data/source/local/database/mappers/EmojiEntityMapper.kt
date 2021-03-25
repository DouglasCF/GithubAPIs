package br.com.fornaro.githubapis.data.source.local.database.mappers

import br.com.fornaro.githubapis.data.source.local.database.entities.EmojiEntity
import br.com.fornaro.githubapis.domain.models.Emoji

typealias EmojiEntityMapperAlias = EntityMapper<EmojiEntity, Emoji>

object EmojiEntityMapper : EmojiEntityMapperAlias {
    override fun fromDomain(domain: Emoji) = with(domain) {
        EmojiEntity(
            name = name,
            url = url
        )
    }

    override fun fromEntity(entity: EmojiEntity) = with(entity) {
        Emoji(
            name = name,
            url = url
        )
    }
}