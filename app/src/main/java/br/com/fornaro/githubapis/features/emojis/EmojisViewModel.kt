package br.com.fornaro.githubapis.features.emojis

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.Emoji
import br.com.fornaro.githubapis.domain.repositories.EmojiRepository
import br.com.fornaro.githubapis.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojisViewModel @Inject constructor(
    private val emojiRepository: EmojiRepository
) : BaseViewModel() {

    private val _state = MutableSharedFlow<EmojisState>()
    val state get() = _state

    init {
        loadEmojis()
    }

    fun loadEmojis() {
        viewModelScope.launch(exceptionHandler) {
            _state.emit(EmojisState.Loading)
            val emojis = emojiRepository.fetchAll()
            _state.emit(EmojisState.Content(emojis))
        }
    }
}

sealed class EmojisState {
    object Loading : EmojisState()
    data class Content(val emojis: List<Emoji>) : EmojisState()
}