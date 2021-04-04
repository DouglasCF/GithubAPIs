package br.com.fornaro.githubapis.domain.repositories

import br.com.fornaro.githubapis.domain.models.Emoji

interface EmojiRepository {

    suspend fun fetchAll(): List<Emoji>
}