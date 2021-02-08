package com.ngoopy.consumerapp.service.model

import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.ngoopy.consumerapp.db.DatabaseContract.UserColumns.Companion.ID
import com.ngoopy.consumerapp.db.DatabaseContract.UserColumns.Companion.URL_AVATAR
import com.ngoopy.consumerapp.db.DatabaseContract.UserColumns.Companion.USERNAME

object ModelFavorite : ViewModel() {
    fun getFavorite(favoriteCursor: Cursor?): ArrayList<ItemsFavorite> {
        val itemsFavorite = ArrayList<ItemsFavorite>()

        favoriteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val urlAvatar = getString(getColumnIndexOrThrow(URL_AVATAR))
                itemsFavorite.add(ItemsFavorite(id, username, urlAvatar))
            }
        }
        return itemsFavorite
    }
}