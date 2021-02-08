package com.submission.githubuser.service.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.githubuser.R
import com.submission.githubuser.databinding.ItemUserBinding
import com.submission.githubuser.service.response.ItemsFavorite
import com.submission.githubuser.ui.detail.DetailActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var lists = ArrayList<ItemsFavorite>()
        set(lists) {
            this.lists.clear()
            this.lists.addAll(lists)
            notifyDataSetChanged()
        }

    class FavoriteViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsFavorite) {
            with(binding) {
                txtName.text = item.username
                Glide.with(root.context)
                    .load(item.url_avatar)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black).error(R.drawable.ic_broken_black))
                    .into(imgPhoto)
                itemView.setOnClickListener {
                    val intentGoToDetail = Intent(root.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_USERNAME, item.username)
                    }
                    root.context.startActivity(intentGoToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    override fun getItemCount(): Int = lists.size
}