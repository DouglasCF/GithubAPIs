package br.com.fornaro.githubapis.features.googlerepos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        setupViewModel()
    }

    private fun setupRecyclerView() = with(binding.rvGoogleRepos) {
        setHasFixedSize(true)
        adapter = viewAdapter
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { result.collect(::handleData) }
    }

    private suspend fun handleData(data: PagingData<Repo>) {
        binding.pbGoogleRepos.isVisible = false
        viewAdapter.submitData(data)
    }
}