package com.submission.githubuser.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "user_favorite"
            const val ID = "id"
            const val USERNAME = "username"
            const val URL_AVATAR = "url_avatar"
        }
    }
}