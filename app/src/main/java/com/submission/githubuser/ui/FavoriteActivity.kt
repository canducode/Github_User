package com.submission.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.githubuser.databinding.ActivityFavoriteBinding
import com.submission.githubuser.service.adapter.FavoriteAdapter
import com.submission.githubuser.service.model.ModelFavorite

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setAdapter()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setAdapter() {
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ModelFavorite::class.java]
        val items = viewModel.getFavorite(this)

        val usersAdapter = FavoriteAdapter()
        usersAdapter.setFavorite(items)

        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = usersAdapter
        }
    }
}