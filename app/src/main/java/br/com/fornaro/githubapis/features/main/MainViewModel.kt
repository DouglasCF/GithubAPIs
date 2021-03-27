package br.com.fornaro.githubapis.features.main

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.usecases.GetRandomEmojiUseCase
import br.com.fornaro.githubapis.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomEmojiUseCase: GetRandomEmojiUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state get() = _state

    fun getRandomEmoji() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = state.value.copy(isLoadingEmojis = true)
            val emoji = getRandomEmojiUseCase()
            _state.value = state.value.copy(isLoadingEmojis = false, emoji = emoji)
        }
    }
}

data class MainState(
    val isLoadingEmojis: Boolean = false,
    val emoji: Emoji? = null
)