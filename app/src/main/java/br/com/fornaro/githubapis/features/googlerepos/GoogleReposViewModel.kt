package br.com.fornaro.githubapis.features.googlerepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.fornaro.githubapis.domain.repositories.GoogleReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoogleReposViewModel @Inject constructor(
    googleReposRepository: GoogleReposRepository
) : ViewModel() {

    val result = googleReposRepository
        .fetch()
        .cachedIn(viewModelScope)
}