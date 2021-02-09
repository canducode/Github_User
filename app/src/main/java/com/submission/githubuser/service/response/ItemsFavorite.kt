package com.submission.githubuser.service.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsFavorite(
    var id: Int = 0,
    var username: String? = null,
    var url_avatar: String? = null
) : Parcelable
