package com.submission.githubuser.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.USERNAME
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.ID
import java.sql.SQLException

class FavoriteHelper(context: Context) {
    companion object {
        private lateinit var database: SQLiteDatabase
        private lateinit var databaseHelper: DatabaseHelper
        private const val DATABASE_TABLE = TABLE_NAME
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
        return database.query(DATABASE_TABLE,null,null,null,null,null,"$ID ASC")
    }

    @SuppressLint("Recycle")
    fun checkByUsername(username: String) : Boolean {
        return database.rawQuery("SELECT * FROM $DATABASE_TABLE WHERE USERNAME = '$username'", null).count != 0
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteByUsername(username: String): Int {
        return database.delete(DATABASE_TABLE, "$USERNAME = '$username'",null)
    }
}