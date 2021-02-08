package com.submission.githubuser.service.api

import com.submission.githubuser.service.response.ItemsFollow
import com.submission.githubuser.service.response.ResponseDetail
import com.submission.githubuser.service.response.ResponseSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val token = "5845a467ff4e03585fb13bd36f7519ee9b29075b"

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token $token")
    fun getSearch(
        @Query("q")username: String
    ) : Call<ResponseSearch>

    @GET("users/{username}")
    fun getDetail(
        @Path("username")username: String
    ) : Call<ResponseDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token  $token")
    fun getFollowers(
        @Path("username")username: String
    ) : Call<ArrayList<ItemsFollow>?>

    @GET("users/{username}/following")
    @Headers("Authorization: token  $token")
    fun getFollowing(
        @Path("username")username: String
    ) : Call<ArrayList<ItemsFollow>?>
}