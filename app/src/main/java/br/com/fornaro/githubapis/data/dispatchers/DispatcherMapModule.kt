package br.com.fornaro.githubapis.data.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherMapModule {

    @Provides
    fun provideDispatcherMap(): DispatcherMap = MainDispatcherMap
}