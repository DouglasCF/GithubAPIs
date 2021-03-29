package br.com.fornaro.githubapis.data.source.remote.api

import br.com.fornaro.githubapis.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("emojis")
    suspend fun fetchEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun fetchUser(@Path("username") username: String): UserResponse
}