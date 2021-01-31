package com.submission.githubuser.service.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.githubuser.R
import com.submission.githubuser.databinding.ItemUserBinding
import com.submission.githubuser.service.response.ItemsFollow

class FollowAdapter : RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    private val list = ArrayList<ItemsFollow>()

    fun setData(items: ArrayList<ItemsFollow>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class FollowViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: ItemsFollow) {
            with(binding) {
                txtName.text = items.login
                Glide.with(root)
                    .load(items.avatar_url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black).error(R.drawable.ic_broken_black))
                    .into(imgPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}