package com.example.test.mapid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.mapid.model.ServiceApi
import com.example.test.mapid.model.BlogData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val blogDataMutable = MutableLiveData<BlogData>()
    val stateMutable = MutableLiveData<State>()

    fun fetchBlog(link : String){
        stateMutable.value = State.LOADING
        ServiceApi.create().callBlog(link).enqueue(object : Callback<BlogData> {
            override fun onFailure(call: Call<BlogData>, t: Throwable) {
                stateMutable.value = State.FAILED

            }

            override fun onResponse(call: Call<BlogData>, response: Response<BlogData>) {
                blogDataMutable.value = response.body()
                stateMutable.value = State.SUCCES
            }
        })
    }

    enum class State{
        LOADING,FAILED,SUCCES
    }
}