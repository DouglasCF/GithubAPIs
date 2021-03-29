package br.com.fornaro.githubapis.domain.error

import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

private const val USER_NOT_FOUND_ERROR_MESSAGE =
    "{\"message\":\"Not Found\",\"documentation_url\":\"https://docs.github.com/rest/reference/users#get-a-user\"}"

class ExceptionMapper(
    private val handler: (ErrorType) -> Unit
) : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    private val exceptions = listOf(
        Pair(UnknownHostException::class, ErrorType.NO_INTERNET)
    )

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        var error = checkApiError(exception)
            ?: exceptions.firstOrNull { it.first.isInstance(exception) }?.second

        if (error == null) {
            error = ErrorType.GENERIC_ERROR
            // Log Firebase Crashlytics
        }

        handler.invoke(error)
    }

    private fun checkApiError(exception: Throwable): ErrorType? {
        if (exception is HttpException) {
            val errorMessage = exception.response()?.errorBody()?.string()
            if (errorMessage == USER_NOT_FOUND_ERROR_MESSAGE) return ErrorType.USER_NOT_FOUND
        }
        return null
    }
}