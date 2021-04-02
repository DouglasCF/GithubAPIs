package br.com.fornaro.githubapis.data.source.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fornaro.githubapis.data.source.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun selectAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE login = :username COLLATE NOCASE")
    suspend fun select(username: String): UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}