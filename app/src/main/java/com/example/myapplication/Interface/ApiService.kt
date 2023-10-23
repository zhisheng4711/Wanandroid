package com.example.myapplication.Interface

import RecycleViewResponse
import com.example.myapplication.Data.UserInfo.RecycleView.projectlist
import com.example.myapplication.Data.UserInfo.RecycleView.projectsortdata
import com.example.myapplication.Data.UserInfo.banner.BannerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
        @GET("banner/json")
        fun getBanners(): Call<BannerResponse>

        @GET("article/list/1/json")
        fun getReceleView():Call<RecycleViewResponse>

        @GET("project/tree/json")
        fun getappendix():Call<projectsortdata>

        @GET("project/list/{page}/json")
        fun getProject(@Path("page") page: Int, @Query("cid") cid: Int): Call<projectlist>

        @POST("article/query/{pageNum}/json")
        fun searchArticles(@Path("pageNum") pageNum: Int, @Query("k") keyword: String): Call<RecycleViewResponse>

//    @GET("xxx")
//    fun getXXX(): Call<XXXResponse>
    }