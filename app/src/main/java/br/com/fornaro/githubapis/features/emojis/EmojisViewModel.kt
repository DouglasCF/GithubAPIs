package br.com.fornaro.githubapis.features.emojis

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojisViewModel @Inject constructor(
    private val emojiRepository: EmojiRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<EmojisState>(EmojisState.Loading)
    val state get() = _state

    fun loadEmojis() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = EmojisState.Loading
            val emojis = emojiRepository.fetchAll()
            _state.value = EmojisState.Content(emojis)
        }
    }

    fun removeEmoji(emoji: Emoji) {
        val content = _state.value as EmojisState.Content
        _state.value = content.copy(emojis = content.emojis.minus(emoji))
    }
}

sealed class EmojisState {
    object Loading : EmojisState()
    data class Content(val emojis: List<Emoji>) : EmojisState()
}