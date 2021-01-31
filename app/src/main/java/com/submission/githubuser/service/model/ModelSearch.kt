package com.submission.githubuser.service.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.submission.githubuser.service.api.RetrofitCall
import com.submission.githubuser.service.response.ItemsSearch
import com.submission.githubuser.service.response.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelSearch : ViewModel() {
    val listSearch = MutableLiveData<ArrayList<ItemsSearch>>()

    fun setSearch(keyword: String) {
        RetrofitCall.create().getSearch(keyword)
            .enqueue(object : Callback<ResponseSearch> {
                override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                    Log.d("Exception", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseSearch>,
                    response: Response<ResponseSearch>
                ) {
                    listSearch.postValue(response.body()?.items as ArrayList<ItemsSearch>?)
                }
            })
    }

    fun getSearch(): LiveData<ArrayList<ItemsSearch>> {
        return listSearch
    }
}