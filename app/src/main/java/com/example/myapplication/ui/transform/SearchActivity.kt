package com.example.myapplication.ui.transform

import RecycleViewResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Network
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val editText: EditText = findViewById(R.id.editSearch)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                updateArticleData()

                return@setOnEditorActionListener true
            }
            false
        }


    }

    private fun updateArticleData() {
        val editText: EditText = findViewById(R.id.editSearch)
        // 发起首页文章API请求
        val pageNum = 1 // 页码
        val searchfor = editText.text.toString()
        Network.apiService.searchArticles(pageNum, searchfor).enqueue(object :Callback<RecycleViewResponse>{
            override fun onResponse(call: Call<RecycleViewResponse>, response: Response<RecycleViewResponse>) {
                if (response.isSuccessful){
                    val receleViewRespose = response.body()?:return
                    val layoutManager = LinearLayoutManager(this@SearchActivity)
                    layoutManager.orientation = LinearLayoutManager.VERTICAL // 设置为垂直方向
                    val recyclerView: RecyclerView = findViewById(R.id.searchrecyclerView)
                    recyclerView.layoutManager = layoutManager  // layoutManager.orientation = LinearLayoutManager.HORIZONTAL // 设置为水平方向
                    val adapter = ArticleAdapter(receleViewRespose.data.datas)  // 使用 dataList.datas 作为适配器的数据源
                    recyclerView.adapter = adapter
                }
            }
            override fun onFailure(call: Call<RecycleViewResponse>, t: Throwable) {
                // 处理请求失败的情况
                t.printStackTrace()
            }

        })
    }

}

