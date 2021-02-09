package com.submission.githubuser.ui

import android.database.ContentObserver
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.githubuser.databinding.ActivityFavoriteBinding
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.submission.githubuser.service.adapter.FavoriteAdapter
import com.submission.githubuser.service.model.ModelFavorite

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: ModelFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = FavoriteAdapter()
        adapter.notifyDataSetChanged()

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ModelFavorite::class.java]

        loadAsync()

        viewModel.getFavorite().observe(this, {
            if (it != null) {
                adapter.lists = it
            }
        })

        val myObserver = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean) {
                loadAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
    }

    private fun loadAsync() {
        viewModel.setFavorite(this@FavoriteActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}