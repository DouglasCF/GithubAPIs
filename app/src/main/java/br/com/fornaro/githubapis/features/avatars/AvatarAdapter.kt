package br.com.fornaro.githubapis.features.avatars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.githubapis.databinding.ItemAvatarBinding
import br.com.fornaro.githubapis.domain.models.User
import coil.load

class AvatarAdapter(
    private val action: (User) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    var list = emptyList<User>()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(UserDiffUtil(value, field))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemAvatarBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        action
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class ViewHolder(
        private val binding: ItemAvatarBinding,
        private val action: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            ivItemAvatar.load(user.imageUrl)
            ivItemAvatar.setOnClickListener { action(user) }
        }
    }

    class UserDiffUtil(
        private val new: List<User>,
        private val old: List<User>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            new[newItemPosition] == old[oldItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            new[newItemPosition].login == old[oldItemPosition].login
    }
}