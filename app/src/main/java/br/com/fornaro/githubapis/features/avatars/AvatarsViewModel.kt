package br.com.fornaro.githubapis.features.avatars

import androidx.lifecycle.viewModelScope
import br.com.fornaro.githubapis.domain.models.User
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import br.com.fornaro.githubapis.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<AvatarsState>(AvatarsState.Loading)
    val state get() = _state

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = AvatarsState.Loading
            val users = userRepository.fetchAll()
            _state.value = AvatarsState.Content(users = users)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(exceptionHandler) {
            userRepository.delete(user)
        }
    }
}

sealed class AvatarsState {
    object Loading : AvatarsState()
    data class Content(val users: List<User>) : AvatarsState()

}