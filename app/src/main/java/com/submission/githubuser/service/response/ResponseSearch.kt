package com.submission.githubuser.service.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseSearch(
	val total_count: Int? = null,
	val incomplete_results: Boolean? = null,
	val items: List<ItemsSearch>? = null
) : Parcelable

@Parcelize
data class ItemsSearch(
	val login: String? = null,
	val avatar_url: String? = null,
) : Parcelable