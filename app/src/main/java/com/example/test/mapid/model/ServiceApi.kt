package com.example.test.mapid.model

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ServiceApi {

    @GET("getlistblogpublic")
    fun callMenu():Call<List<MenuData>>

    @GET("years_type")
    fun callYear():Call<List<YearData>>

    @GET("getblogpublic/{path}")
    fun callBlog(@Path("path") link: String):Call<BlogData>




    companion object{
        private var api : ServiceApi?= null
        fun create() : ServiceApi {
            if (api == null){
                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.mapid.io/blog/")
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .client(httpClient.build())
                    .build()
                api = retrofit.create(ServiceApi::class.java)
            }
            return api!!
        }
    }
}