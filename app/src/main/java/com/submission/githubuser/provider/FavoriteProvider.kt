package com.submission.githubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.widget.Toast
import com.submission.githubuser.db.DatabaseContract.AUTHORITY
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.submission.githubuser.db.FavoriteHelper

class FavoriteProvider : ContentProvider() {
    companion object {
        private const val FAVORITE = 1
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favoriteHelper: FavoriteHelper

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE)
        }
    }

    override fun onCreate(): Boolean {
        favoriteHelper = FavoriteHelper.getInstance(context as Context)
        favoriteHelper.open()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?,
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            FAVORITE -> favoriteHelper.queryAll()
            else -> favoriteHelper.checkByUsername(uri.lastPathSegment.toString())
        }
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = favoriteHelper.insert(values)
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleted: Int = favoriteHelper.deleteByUsername(uri.lastPathSegment.toString())
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return deleted
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?,
    ): Int = 0
}