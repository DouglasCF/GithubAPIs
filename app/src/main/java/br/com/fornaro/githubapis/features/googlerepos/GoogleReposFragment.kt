package br.com.fornaro.githubapis.features.googlerepos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentGoogleReposBinding
import br.com.fornaro.githubapis.domain.models.Repo
import br.com.fornaro.githubapis.features.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GoogleReposFragment :
    BaseFragment<FragmentGoogleReposBinding>(R.layout.fragment_google_repos) {

    private val viewModel: GoogleReposViewModel by viewModels()

    private val viewAdapter: GoogleReposAdapter by lazy(LazyThreadSafetyMode.NONE) { GoogleReposAdapter() }

    override fun bind(view: View) = FragmentGoogleReposBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRetryButton()
        setupViewModel()
    }

    private fun setupRetryButton() = with(binding.bGoogleReposRetry) {
        setOnClickListener { viewAdapter.retry() }
    }

    private fun setupRecyclerView() = with(binding.rvGoogleRepos) {
        setHasFixedSize(true)
        adapter = viewAdapter.withLoadStateHeaderAndFooter(
            header = GoogleReposStateAdapter { viewAdapter.retry() },
            footer = GoogleReposStateAdapter { viewAdapter.retry() }
        )
        viewAdapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading &&
                    viewAdapter.itemCount == 0
            showEmptyList(isEmptyList)

            binding.rvGoogleRepos.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.pbGoogleRepos.isVisible = loadState.source.refresh is LoadState.Loading
            binding.bGoogleReposRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun showEmptyList(isEmptyList: Boolean) {
        binding.tvGoogleReposEmptyMessage.isVisible = isEmptyList
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { result.collect(::handleData) }
    }

    private suspend fun handleData(data: PagingData<Repo>) {
        binding.pbGoogleRepos.isVisible = false
        viewAdapter.submitData(data)
    }
}