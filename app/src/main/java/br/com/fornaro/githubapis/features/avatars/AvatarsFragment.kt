package br.com.fornaro.githubapis.features.avatars

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentAvatarsBinding
import br.com.fornaro.githubapis.features.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AvatarsFragment : BaseFragment<FragmentAvatarsBinding>(R.layout.fragment_avatars) {

    private val viewModel: AvatarsViewModel by viewModels()

    private val viewAdapter: AvatarAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AvatarAdapter(viewModel::deleteUser)
    }

    override fun bind(view: View) = FragmentAvatarsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() = with(binding.rvAvatars) {
        setHasFixedSize(true)
        adapter = viewAdapter
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { state.collect(::handleState) }
    }

    private fun handleState(state: AvatarsState) {
        handleLoading(false)
        when (state) {
            is AvatarsState.Content -> handleContent(state)
            AvatarsState.Loading -> handleLoading(true)
        }
    }

    private fun handleContent(content: AvatarsState.Content) {
        if (content.users.isEmpty()) {
            binding.gAvatarsEmpty.isVisible = true
        } else {
            binding.gAvatarsEmpty.isVisible = false
            viewAdapter.list = content.users
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.pbAvatars.isVisible = loading
    }
}