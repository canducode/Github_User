package com.submission.githubuser.service.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.submission.githubuser.service.api.RetrofitCall
import com.submission.githubuser.service.response.ItemsFollow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelFollowers : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<ItemsFollow>?>()

    fun setFollowers(username: String) {
        RetrofitCall.create().getFollowers(username)
            .enqueue(object : Callback<ArrayList<ItemsFollow>?> {
                override fun onFailure(call: Call<ArrayList<ItemsFollow>?>, t: Throwable) {
                    Log.d("Exception", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<ItemsFollow>?>,
                    response: Response<ArrayList<ItemsFollow>?>
                ) {
                    listFollowers.postValue(response.body() as ArrayList<ItemsFollow>)
                }
            })
    }

    fun getFollowers(): LiveData<ArrayList<ItemsFollow>?> {
        return listFollowers
    }
}