package br.com.fornaro.githubapis.features.avatars

import android.os.Bundle
import android.view.View
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.databinding.FragmentAvatarsBinding
import br.com.fornaro.githubapis.features.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvatarsFragment : BaseFragment<FragmentAvatarsBinding>(R.layout.fragment_avatars) {

    private lateinit var viewModel: AvatarsViewModel

    override fun bind(view: View) = FragmentAvatarsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}