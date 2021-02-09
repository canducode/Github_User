package com.submission.githubuser.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.USERNAME
import java.sql.SQLException

class FavoriteHelper(context: Context) {
    companion object {
        private lateinit var database: SQLiteDatabase
        private lateinit var databaseHelper: DatabaseHelper
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: FavoriteHelper? = null
        fun getInstance(context: Context): FavoriteHelper =
            INSTANCE ?: synchronized( this) {
                INSTANCE ?: FavoriteHelper(context)
            }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen) database.close()
    }

    fun queryAll(): Cursor? {
        return database.rawQuery("SELECT * FROM $DATABASE_TABLE", null)
    }

    fun checkByUsername(username: String) : Cursor? {
        return database.rawQuery("SELECT * FROM $DATABASE_TABLE WHERE USERNAME = '$username'", null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteByUsername(username: String): Int {
        return database.delete(DATABASE_TABLE, "$USERNAME = '$username'",null)
    }
}