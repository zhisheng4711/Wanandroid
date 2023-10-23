package com.example.myapplication.ui.slideshow.fragments.morefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.Data.UserInfo.RecycleView.projectsortdata
import com.example.myapplication.Network
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMoreBinding
import com.example.myapplication.ui.slideshow.StaggeredGridItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreFragment : Fragment() {
    // 声明一个可空的视图绑定变量，用于与 Fragment 的布局文件进行绑定
    private var _binding: FragmentMoreBinding? = null

    // 创建一个 getter 属性，用于访问视图绑定对象，确保在非空情况下使用
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateProjectData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 通过视图绑定来关联 Fragment 的布局
        _binding = FragmentMoreBinding.inflate(inflater, container, false)

        // 获取根视图，它是 Fragment 布局的顶级视图
        val root: View = binding.root
        val recyclerView = binding.morerecyclerView
        val spacing = resources.getDimensionPixelSize(R.dimen.spacing) // 获取间距的尺寸
        val itemDecoration = StaggeredGridItemDecoration(spacing)
        recyclerView.addItemDecoration(itemDecoration)

        return root
    }

    private fun updateProjectData() {
        // 发起首页文章API请求
        Network.apiService.getappendix().enqueue(object : Callback<projectsortdata> {
            override fun onResponse(call: Call<projectsortdata>, response: Response<projectsortdata>) {
                if (response.isSuccessful) {
                    // 请求成功，处理响应数据
                    val appendixReponse = response.body()
                    // 对projectList进行处理
                    val recyclerView = binding.morerecyclerView
                    val adapter = appendixReponse?.data?.let { MoreFragmentAdapter(it) }  // 使用 dataList.datas 作为适配器的数据源
                    recyclerView.adapter = adapter

                } else {
                    // 请求失败，处理错误
                    val errorMessage = "请求失败: ${response.code()}"
                    // 处理错误
                }
            }

            override fun onFailure(call: Call<projectsortdata>, t: Throwable) {
                // 网络请求失败，处理异常
                val errorMessage = "网络请求失败: ${t.message}"
                // 处理异常
            }
        })
    }

}