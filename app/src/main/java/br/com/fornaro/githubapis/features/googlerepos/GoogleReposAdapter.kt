package br.com.fornaro.githubapis.features.googlerepos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.githubapis.databinding.ItemGoogleReposBinding
import br.com.fornaro.githubapis.domain.models.Repo

class GoogleReposAdapter : RecyclerView.Adapter<GoogleReposAdapter.ViewHolder>() {

    var list = emptyList<Repo>()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(UserDiffUtil(value, field))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGoogleReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    class ViewHolder(
        private val binding: ItemGoogleReposBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) = with(binding) {
            tvItemGoogleRepos.text = repo.fullName
        }
    }

    class UserDiffUtil(
        private val new: List<Repo>,
        private val old: List<Repo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            new[newItemPosition] == old[oldItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            new[newItemPosition].fullName == old[oldItemPosition].fullName
    }
}