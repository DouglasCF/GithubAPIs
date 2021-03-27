package br.com.fornaro.githubapis.features.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentMainBinding
import br.com.fornaro.githubapis.features.BaseFragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun bind(view: View) = FragmentMainBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRandomEmojiButton()
        setupEmojiListButton()
        setupViewModel()
    }

    private fun setupRandomEmojiButton() = with(binding.bMainRandomEmoji) {
        setOnClickListener { viewModel.getRandomEmoji() }
    }

    private fun setupEmojiListButton() = with(binding.bMainEmojiList) {
        setOnClickListener { findNavController().navigate(R.id.action_emojisFragment) }
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { state.collect(::handleState) }
        lifecycleScope.launch { message.collect(::handleMessage) }
    }

    private fun handleState(state: MainState) {
        binding.imMain.load(state.emoji?.url)
    }
}