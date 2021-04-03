package br.com.fornaro.githubapis.features.googlerepos

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.Repo
import br.com.fornaro.githubapis.features.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GoogleReposViewModel : BaseViewModel() {

    private val _state = MutableStateFlow<GoogleReposState>(GoogleReposState.Loading)
    val state get() = _state

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = GoogleReposState.Loading
            delay(2000)
            _state.value = GoogleReposState.Content(listOf(Repo("repo 1"),Repo("repo 2")))
        }
    }
}

sealed class GoogleReposState {
    object Loading : GoogleReposState()
    data class Content(val repos: List<Repo>) : GoogleReposState()
}