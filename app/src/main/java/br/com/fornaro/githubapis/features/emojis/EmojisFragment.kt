package br.com.fornaro.githubapis.features.emojis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentEmojisBinding
import br.com.fornaro.githubapis.features.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmojisFragment : BaseFragment<FragmentEmojisBinding>(R.layout.fragment_emojis) {

    private val viewModel: EmojisViewModel by viewModels()

    private val viewAdapter by lazy(LazyThreadSafetyMode.NONE) { EmojiAdapter(viewModel::removeEmoji) }

    override fun bind(view: View) = FragmentEmojisBinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadEmojis()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSwipeRefresh()
        setupViewModel()
    }

    private fun setupRecyclerView() = with(binding.rvEmojis) {
        setHasFixedSize(true)
        adapter = viewAdapter
    }

    private fun setupSwipeRefresh() = with(binding.srEmojis) {
        setOnRefreshListener { viewModel.loadEmojis() }
        setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { state.collect(::handleState) }
        lifecycleScope.launch { message.collect(::handleMessage) }
    }

    private fun handleState(state: EmojisState) {
        handleLoading(false)
        when (state) {
            is EmojisState.Content -> handleContent(state)
            EmojisState.Loading -> handleLoading(true)
        }
    }

    private fun handleContent(content: EmojisState.Content) {
        viewAdapter.list = content.emojis
    }

    private fun handleLoading(loading: Boolean) {
        binding.srEmojis.isRefreshing = loading
    }
}