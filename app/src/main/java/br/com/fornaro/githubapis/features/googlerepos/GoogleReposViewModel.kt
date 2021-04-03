package br.com.fornaro.githubapis.features.googlerepos

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.Repo
import br.com.fornaro.githubapis.domain.repositories.GoogleReposRepository
import br.com.fornaro.githubapis.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleReposViewModel @Inject constructor(
    private val googleReposRepository: GoogleReposRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<GoogleReposState>(GoogleReposState.Loading)
    val state get() = _state

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = GoogleReposState.Loading
            val repos = googleReposRepository.fetch()
            _state.value = GoogleReposState.Content(repos = repos)
        }
    }
}

sealed class GoogleReposState {
    object Loading : GoogleReposState()
    data class Content(val repos: List<Repo>) : GoogleReposState()
}