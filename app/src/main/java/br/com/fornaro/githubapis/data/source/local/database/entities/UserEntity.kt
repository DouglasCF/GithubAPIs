package br.com.fornaro.githubapis.data.source.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val login: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
)
