package br.com.fornaro.githubapis.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.error.ErrorType
import br.com.fornaro.githubapis.domain.error.ExceptionMapper
import br.com.fornaro.githubapis.domain.event.MessageEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _message = MutableSharedFlow<MessageEvent>()
    val message get() = _message

    protected val exceptionHandler = ExceptionMapper(::handleError)

    private fun handleError(error: ErrorType) {
        viewModelScope.launch {
            val message = when (error) {
                ErrorType.NO_INTERNET -> MessageEvent.NO_INTERNET
                ErrorType.USER_NOT_FOUND -> MessageEvent.USER_NOT_FOUND
                else -> MessageEvent.GENERIC_MESSAGE
            }
            _message.emit(message)
        }
    }
}