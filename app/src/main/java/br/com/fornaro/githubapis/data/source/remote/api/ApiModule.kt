package br.com.fornaro.githubapis.data.source.remote.api

import br.com.fornaro.githubapis.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRetrofit(moshi: Moshi) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideGithubService(retrofit: Retrofit) = retrofit
        .create(GithubApi::class.java)
}