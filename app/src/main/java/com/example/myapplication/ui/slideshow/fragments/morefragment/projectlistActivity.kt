package com.example.myapplication.ui.slideshow.fragments.morefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.UserInfo.RecycleView.projectlist
import com.example.myapplication.Network
import com.example.myapplication.R
import com.example.myapplication.ui.slideshow.ProjectAdapter
import com.example.myapplication.ui.slideshow.StaggeredGridItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class projectlistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projectlist)
        val extraData = intent.getIntExtra("id",0)
        Log.d("接收的链接", "接收的数据是$extraData")
        updateProjectData(extraData)
    }

    private fun updateProjectData(cid:Int) {
        // 发起首页文章API请求
        val page = 0 // 页码
        Network.apiService.getProject(page, cid).enqueue(object : Callback<projectlist> {
            override fun onResponse(call: Call<projectlist>, response: Response<projectlist>) {
                if (response.isSuccessful) {
                    // 请求成功，处理响应数据
                    val projectListReponse = response.body()
                    // 对projectList进行处理
                    val recyclerView:RecyclerView = findViewById(R.id.morerecyclerView)
                    val adapter = projectListReponse?.data?.let { ProjectAdapter(it.datas) }
                    val spacing = resources.getDimensionPixelSize(R.dimen.spacing) // 获取间距的尺寸
                    val itemDecoration = StaggeredGridItemDecoration(spacing)
                    recyclerView.addItemDecoration(itemDecoration)// 使用 dataList.datas 作为适配器的数据源
                    recyclerView.adapter = adapter

                } else {
                    // 请求失败，处理错误
                    val errorMessage = "请求失败: ${response.code()}"
                    // 处理错误
                }
            }

            override fun onFailure(call: Call<projectlist>, t: Throwable) {
                // 网络请求失败，处理异常
                val errorMessage = "网络请求失败: ${t.message}"
                // 处理异常
            }
        })
    }

}