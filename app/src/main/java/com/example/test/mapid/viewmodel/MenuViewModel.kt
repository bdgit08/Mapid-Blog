package com.example.test.mapid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.mapid.model.ServiceApi
import com.example.test.mapid.model.MenuData
import com.example.test.mapid.model.YearData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel() {
    var mutable = MutableLiveData<Map<String,List<MenuData>>>()

    fun fetchYear(){
        ServiceApi.create().callYear().enqueue(object : Callback<List<YearData>>{
            override fun onFailure(call: Call<List<YearData>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<YearData>>,
                response: Response<List<YearData>>
            ) {
              fetchMenu(response.body()!!)
            }

        })
    }


    fun fetchMenu( list : List<YearData>){
        ServiceApi.create().callMenu().enqueue(object : Callback<List<MenuData>>{
            override fun onFailure(call: Call<List<MenuData>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<MenuData>>,
                response: Response<List<MenuData>>
            ) {
                val map = mutableMapOf<String,List<MenuData>>()
                val listMenu = response.body() ?: mutableListOf()
                for (year in list){
                    val menus = mutableListOf<MenuData>()
                     for (menu in listMenu){
                          if (year.sub_type.equals(menu.sub_type)){
                              menus.add(menu)
                          }
                     }
                    year.sub_type?.let {
                        map.put(it,menus)
                    }
                }

                mutable.value = map
            }

        })
    }
}