package com.submission.githubuser.service.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsFollow(
    val login: String? = null,
    val avatar_url: String? = null,
) : Parcelable