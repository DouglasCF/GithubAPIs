package br.com.fornaro.githubapis.features.googlerepos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.githubapis.databinding.ItemGoogleReposBinding
import br.com.fornaro.githubapis.domain.models.Repo

class GoogleReposAdapter : PagingDataAdapter<Repo, GoogleReposAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGoogleReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    class ViewHolder(
        private val binding: ItemGoogleReposBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) = with(binding) {
            tvItemGoogleRepos.text = repo.fullName
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }
}