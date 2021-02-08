package com.ngoopy.consumerapp

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ngoopy.consumerapp.service.adapter.FavoriteAdapter
import com.ngoopy.consumerapp.databinding.ActivityMainBinding
import com.ngoopy.consumerapp.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.ngoopy.consumerapp.db.DatabaseContract.UserColumns.Companion.ID
import com.ngoopy.consumerapp.service.model.ModelFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.setHasFixedSize(true)
        adapter = FavoriteAdapter()
        binding.rvFavorite.adapter = adapter

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadFavoriteAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
        loadFavoriteAsync()
    }

    private fun loadFavoriteAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, "$ID ASC")
                ModelFavorite.getFavorite(cursor)
            }

            val itemFavorites = deferred.await()
            if (itemFavorites.size > 0) {
                adapter.lists = itemFavorites
            } else {
                adapter.lists = ArrayList()
                showSnackBarMessage()
            }
        }
    }

    private fun showSnackBarMessage() {
        Snackbar.make(binding.rvFavorite, "Tidak ada data sekarang", Snackbar.LENGTH_SHORT).show()
    }
}