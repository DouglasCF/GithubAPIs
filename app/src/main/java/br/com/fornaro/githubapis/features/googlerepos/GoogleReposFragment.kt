package br.com.fornaro.githubapis.features.googlerepos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentGoogleReposBinding
import br.com.fornaro.githubapis.features.BaseFragment

class GoogleReposFragment :
    BaseFragment<FragmentGoogleReposBinding>(R.layout.fragment_google_repos) {

    private val viewModel: GoogleReposViewModel by viewModels()

    private val viewAdapter: GoogleReposAdapter by lazy(LazyThreadSafetyMode.NONE) { GoogleReposAdapter() }

    override fun bind(view: View) = FragmentGoogleReposBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.rvGoogleRepos) {
        setHasFixedSize(true)
        adapter = viewAdapter
    }
}