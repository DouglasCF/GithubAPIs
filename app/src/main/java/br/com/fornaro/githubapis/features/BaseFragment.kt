package br.com.fornaro.githubapis.features

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import br.com.fornaro.githubapis.R
import br.com.fornaro.githubapis.domain.event.MessageEvent
import br.com.fornaro.githubapis.extensions.toast

abstract class BaseFragment<T : ViewBinding>(@LayoutRes layout: Int) : Fragment(layout) {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun handleMessage(message: MessageEvent) {
        when (message) {
            MessageEvent.NO_INTERNET -> toast(R.string.message_event_no_internet)
            MessageEvent.GENERIC_MESSAGE -> toast(R.string.message_event_generic)
        }
    }

    abstract fun bind(view: View): T
}