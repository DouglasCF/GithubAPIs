package br.com.fornaro.githubapis.data.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherMap {

    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val computation: CoroutineDispatcher
}
