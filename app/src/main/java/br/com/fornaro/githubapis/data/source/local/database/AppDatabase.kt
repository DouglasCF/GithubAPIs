package br.com.fornaro.githubapis.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fornaro.githubapis.data.source.local.database.daos.EmojiDao
import br.com.fornaro.githubapis.data.source.local.database.daos.UserDao
import br.com.fornaro.githubapis.data.source.local.database.entities.EmojiEntity
import br.com.fornaro.githubapis.data.source.local.database.entities.UserEntity

@Database(
    entities = [
        EmojiEntity::class,
        UserEntity::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun userDao(): UserDao
}