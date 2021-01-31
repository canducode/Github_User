package com.submission.githubuser.service.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.submission.githubuser.service.api.RetrofitCall
import com.submission.githubuser.service.response.ResponseDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelDetail : ViewModel() {
    val dataDetail = MutableLiveData<ResponseDetail>()

    fun setDetail(username: String) {
        RetrofitCall.create().getDetail(username)
            .enqueue(object : Callback<ResponseDetail> {
                override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                    Log.d("Exception", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseDetail>,
                    response: Response<ResponseDetail>
                ) {
                    Log.d("Info", "Success")
                    dataDetail.postValue(response.body())
                }
            })
    }

    fun getDetail(): LiveData<ResponseDetail> = dataDetail
}