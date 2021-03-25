package br.com.fornaro.githubapis.data.source.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fornaro.githubapis.data.source.local.database.entities.EmojiEntity

@Dao
interface EmojiDao {

    @Query("SELECT * FROM emoji")
    suspend fun fetchAll(): List<EmojiEntity>

    @Insert
    suspend fun insert(list: List<EmojiEntity>)
}