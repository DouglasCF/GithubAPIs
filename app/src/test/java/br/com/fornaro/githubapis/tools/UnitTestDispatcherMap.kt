package br.com.fornaro.githubapis.tools

import br.com.fornaro.githubapis.data.dispatchers.DispatcherMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object UnitTestDispatcherMap : DispatcherMap {

    override val io: CoroutineDispatcher = Dispatchers.Unconfined
    override val ui: CoroutineDispatcher = Dispatchers.Unconfined
    override val computation: CoroutineDispatcher = Dispatchers.Unconfined
}
