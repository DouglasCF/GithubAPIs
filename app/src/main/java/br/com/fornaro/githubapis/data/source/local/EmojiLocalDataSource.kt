package br.com.fornaro.githubapis.data.source.local

import br.com.fornaro.githubapis.domain.models.Emoji

interface EmojiLocalDataSource {

    suspend fun fetchAll(): List<Emoji>
    suspend fun insert(emojis: List<Emoji>)
}