package br.com.fornaro.githubapis.data.source.remote.mappers

import br.com.fornaro.githubapis.domain.models.Emoji

typealias EmojiMapperAlias = Mapper<Map<String, String>, List<Emoji>>

object EmojiMapper : EmojiMapperAlias {
    override fun map(input: Map<String, String>) = input.map {
        Emoji(
            name = it.key,
            url = it.value
        )
    }
}