package br.com.fornaro.githubapis.data.source.remote.response

import com.squareup.moshi.Json

data class RepoResponse(
    @Json(name = "full_name") val fullName: String
)
