package br.com.fornaro.githubapis.domain.error

import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.UnknownHostException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class ExceptionMapper(
    private val handler: (ErrorType) -> Unit
) : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    private val exceptions = listOf(
        Pair(UnknownHostException::class, ErrorType.NO_INTERNET)
    )

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        var error = exceptions.firstOrNull { it.first.isInstance(exception) }?.second

        if (error == null) {
            error = ErrorType.GENERIC_ERROR
            // Log Firebase Crashlytics
        }

        handler.invoke(error)
    }
}