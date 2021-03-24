package br.com.fornaro.githubapis.data.source.remote.api

import retrofit2.http.GET

interface GithubApi {

    @GET("emojis")
    suspend fun fetchEmojis(): Map<String, String>
}