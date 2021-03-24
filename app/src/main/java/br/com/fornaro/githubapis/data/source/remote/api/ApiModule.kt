package br.com.fornaro.githubapis.data.source.remote.api

import br.com.fornaro.githubapis.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideGithubService(retrofit: Retrofit) = retrofit
        .create(GithubApi::class.java)
}