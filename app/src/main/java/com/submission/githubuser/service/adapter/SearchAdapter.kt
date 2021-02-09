package com.submission.githubuser.service.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.githubuser.R
import com.submission.githubuser.databinding.ItemUserBinding
import com.submission.githubuser.service.response.ItemsSearch
import com.submission.githubuser.ui.detail.DetailActivity

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val list = ArrayList<ItemsSearch>()

    fun setData(items: ArrayList<ItemsSearch>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class SearchViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemsSearch: ItemsSearch) {
            with(binding){
                txtName.text = itemsSearch.login
                Glide.with(root)
                    .load(itemsSearch.avatar_url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black).error(R.drawable.ic_broken_black))
                    .into(imgPhoto)
                itemView.setOnClickListener {
                    val intentGoToDetail = Intent(root.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_USERNAME, itemsSearch.login)
                    }
                    root.context.startActivity(intentGoToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}