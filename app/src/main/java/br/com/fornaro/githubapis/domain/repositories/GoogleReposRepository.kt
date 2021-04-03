package br.com.fornaro.githubapis.domain.repositories

import androidx.paging.PagingData
import br.com.fornaro.githubapis.domain.models.Repo
import kotlinx.coroutines.flow.Flow

interface GoogleReposRepository {

    fun fetch(): Flow<PagingData<Repo>>
}