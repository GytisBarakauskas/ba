package com.gytisdev.bahometask.posts.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gytisdev.bahometask.databinding.ItemPostHeaderBinding
import com.gytisdev.bahometask.posts.data.model.Post

class MainPostsAdapter(
    private var items: List<Post> = emptyList(),
    val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<MainPostsAdapter.PostHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHeaderViewHolder {
        val binding = ItemPostHeaderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHeaderViewHolder(binding)
    }

    inner class PostHeaderViewHolder(val binding: ItemPostHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: PostHeaderViewHolder, position: Int) {
        with(holder.binding) {
            val item = items[position]
            title.text = item.title
            root.setOnClickListener {
                onItemSelected(item.id)
            }
        }
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Post>) {
        this.items = items
        notifyDataSetChanged()
    }
}