package br.com.fornaro.githubapis.data.source.remote.mappers

import br.com.fornaro.githubapis.domain.models.Emoji

typealias EmojiMapperAlias = Mapper<Map.Entry<String, String>, Emoji>

object EmojiMapper : EmojiMapperAlias {
    override fun map(input: Map.Entry<String, String>) = with(input) {
        Emoji(
            name = key,
            url = value
        )
    }
}