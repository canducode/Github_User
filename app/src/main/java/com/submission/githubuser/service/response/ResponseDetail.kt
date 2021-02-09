package com.submission.githubuser.service.response

data class ResponseDetail(
	var avatar_url: String? = null,
	var login: String? = null,
	var name: String? = null,
	var followers: Int? = null,
	var following: Int? = null,
	var company: String? = null,
	var location: String? = null,
	var public_repos: Int? = null
)