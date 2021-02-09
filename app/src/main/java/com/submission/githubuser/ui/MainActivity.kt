package com.submission.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.githubuser.R
import com.submission.githubuser.databinding.ActivityMainBinding
import com.submission.githubuser.service.adapter.SearchAdapter
import com.submission.githubuser.service.model.ModelSearch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var modelSearch: ModelSearch
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        adapter = SearchAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvSearchUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvSearchUser.adapter = adapter

            modelSearch = ViewModelProvider(this@MainActivity, ViewModelProvider.NewInstanceFactory()).get(ModelSearch::class.java)
        }

        modelSearch.getSearch().observe(this, {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
                binding.tvWelcome.visibility = View.GONE
            }
        })

        binding.btnSearch.setOnClickListener {
            val searchKeyword = binding.edtSearch.text.toString()

            if (searchKeyword.isEmpty()) {
                binding.layEdtSearch.error = "Masih kosong"
            } else {
                binding.layEdtSearch.isErrorEnabled = false
                setAdapter(binding.edtSearch.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter(keyword: String) {
        showLoading(true)

        modelSearch.setSearch(keyword)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}