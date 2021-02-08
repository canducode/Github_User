package com.submission.githubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.URL_AVATAR
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.USERNAME
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.ID

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbgithubuser"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE $TABLE_NAME" +
                "(${ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USERNAME TEXT NOT NULL," +
                "$URL_AVATAR TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
}
