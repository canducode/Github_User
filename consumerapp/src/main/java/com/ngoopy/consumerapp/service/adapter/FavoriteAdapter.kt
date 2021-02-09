package com.ngoopy.consumerapp.service.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ngoopy.consumerapp.R
import com.ngoopy.consumerapp.databinding.ItemUserBinding
import com.ngoopy.consumerapp.service.model.ItemsFavorite

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var lists = ArrayList<ItemsFavorite>()
        set(lists) {
            if (lists.size > 0) {
                this.lists.clear()
            }
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