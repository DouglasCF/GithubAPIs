package br.com.fornaro.githubapis.data.source.remote.response

import com.squareup.moshi.Json

data class UserResponse(
    val login: String,
    val id: Long,
    @Json(name = "avatar_url") val avatarUrl: String,
)
