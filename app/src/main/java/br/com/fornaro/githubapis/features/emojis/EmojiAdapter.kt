package br.com.fornaro.githubapis.features.emojis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.githubapis.databinding.ItemEmojiBinding
import br.com.fornaro.githubapis.domain.models.Emoji
import coil.load

class EmojiAdapter : RecyclerView.Adapter<EmojiAdapter.ViewHolder>() {

    var list = emptyList<Emoji>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemEmojiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class ViewHolder(private val binding: ItemEmojiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emoji: Emoji) = with(binding) {
            ivItemEmoji.load(emoji.url)
        }
    }
}