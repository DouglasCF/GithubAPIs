package br.com.fornaro.githubapis.features.googlerepos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentGoogleReposBinding
import br.com.fornaro.githubapis.features.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        lifecycleScope.launch { state.collect(::handleState) }
        lifecycleScope.launch { message.collect(::handleMessage) }
    }

    private fun handleState(state: GoogleReposState) {
        handleLoading(false)
        when (state) {
            GoogleReposState.Loading -> handleLoading(true)
            is GoogleReposState.Content -> handleContent(state)
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.pbGoogleRepos.isVisible = loading
    }

    private fun handleContent(content: GoogleReposState.Content) {
        viewAdapter.list = content.repos
    }
}