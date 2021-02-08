package com.submission.githubuser.service.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.submission.githubuser.db.DatabaseContract
import com.submission.githubuser.service.response.ItemsFavorite

class ModelFavorite : ViewModel() {
    val listFavorite = MutableLiveData<ArrayList<ItemsFavorite>?>()

    fun setFavorite(context: Context){
        val cursor = context.contentResolver.query(DatabaseContract.UserColumns.CONTENT_URI, null, null, null, "${DatabaseContract.UserColumns.ID} ASC")
        val itemsFavorite = ArrayList<ItemsFavorite>()
        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.ID))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                val urlAvatar = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.URL_AVATAR))
                itemsFavorite.add(ItemsFavorite(id, username, urlAvatar))
            }
        }
        listFavorite.postValue(itemsFavorite)
    }

    fun getFavorite(): LiveData<ArrayList<ItemsFavorite>?> {
        return listFavorite
    }
}