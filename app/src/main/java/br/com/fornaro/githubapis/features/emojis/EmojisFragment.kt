package br.com.fornaro.githubapis.features.emojis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentEmojisBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmojisFragment : Fragment(R.layout.fragment_emojis) {

    private val viewModel: EmojisViewModel by viewModels()

    private val viewAdapter by lazy(LazyThreadSafetyMode.NONE) { EmojiAdapter() }

    private var _binding: FragmentEmojisBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmojisBinding.bind(view)
        setupRecyclerView()
        setupViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = with(binding.rvEmojis) {
        setHasFixedSize(true)
        adapter = viewAdapter
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { state.collect(::handleState) }
    }

    private fun handleState(state: EmojisState) = when (state) {
        is EmojisState.Content -> handleContent(state)
        EmojisState.Loading -> {

        }
    }

    private fun handleContent(content: EmojisState.Content) {
        viewAdapter.list = content.emojis
    }
}