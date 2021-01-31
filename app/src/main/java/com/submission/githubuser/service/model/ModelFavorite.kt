package com.submission.githubuser.service.model

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.submission.githubuser.db.DatabaseContract
import com.submission.githubuser.db.FavoriteHelper
import com.submission.githubuser.service.response.ItemsFavorite

class ModelFavorite : ViewModel() {
   private lateinit var favoriteHelper: FavoriteHelper

    fun getFavorite(context: Context): ArrayList<ItemsFavorite>? {
        favoriteHelper = FavoriteHelper(context)
        favoriteHelper.open()

        val favoriteCurson: Cursor? = favoriteHelper.queryAll()
        val itemsFavorite: ArrayList<ItemsFavorite>? = null

        favoriteCurson?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.ID))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                val urlAvatar = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.URL_AVATAR))
                itemsFavorite?.add(ItemsFavorite(id, username, urlAvatar))
            }
        }

        favoriteHelper.close()
        return itemsFavorite
    }
}