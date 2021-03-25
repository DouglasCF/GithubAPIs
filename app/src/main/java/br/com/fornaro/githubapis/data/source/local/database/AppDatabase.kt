package br.com.fornaro.githubapis.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fornaro.githubapis.data.source.local.database.daos.EmojiDao
import br.com.fornaro.githubapis.data.source.local.database.entities.EmojiEntity

@Database(entities = [EmojiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
}