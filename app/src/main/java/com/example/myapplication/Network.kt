package com.example.myapplication

import com.example.myapplication.Interface.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    // 使用懒加载方式创建 Retrofit 实例，懒加载可以延迟初始化，提高性能
    private val retrofit: Retrofit =
        Retrofit.Builder()
            // 设置基本的 API 地址
            .baseUrl("https://www.wanandroid.com/")
            // 添加 Gson 转换器，用于将 JSON 数据转换为对象
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    // 创建 Gson 实例，用于解析 JSON 数据


    // 使用懒加载方式创建 ApiService 实例，该接口定义了与 API 通信的方法
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}