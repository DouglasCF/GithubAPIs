package br.com.fornaro.githubapis.data.source.remote.api

import br.com.fornaro.githubapis.data.source.remote.response.RepoResponse
import br.com.fornaro.githubapis.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("emojis")
    suspend fun fetchEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun fetchUser(@Path("username") username: String): UserResponse

    @GET("users/{username}/repos")
    suspend fun fetchRepos(
        @Path("username") username: String,
        @Query("page") page: Int = 1,
        @Query("per_page") size: Int = 10
    ): List<RepoResponse>
}