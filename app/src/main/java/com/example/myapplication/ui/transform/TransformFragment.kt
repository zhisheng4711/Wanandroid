package com.example.myapplication.ui.transform

import RecycleViewResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.Data.UserInfo.banner.BannerResponse
import com.example.myapplication.Network
import com.example.myapplication.databinding.FragmentTransformBinding
import com.youth.banner.indicator.CircleIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TransformFragment() : Fragment() {

    // 声明一个可空的视图绑定变量，用于与 Fragment 的布局文件进行绑定
    private var _binding: FragmentTransformBinding? = null

    // 创建一个 getter 属性，用于访问视图绑定对象，确保在非空情况下使用
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateArticleData()
        updateBannerData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 通过视图绑定来关联 Fragment 的布局
        _binding = FragmentTransformBinding.inflate(inflater, container, false)

        // 获取根视图，它是 Fragment 布局的顶级视图
        val root: View = binding.root


        val swipeRefresh:SwipeRefreshLayout = binding.refreshLayout

        // 设置刷新监听器
        swipeRefresh.setOnRefreshListener {
            updateArticleData()
            // 停止刷新动画
            val swipeRefresh:SwipeRefreshLayout = binding.refreshLayout
            swipeRefresh.isRefreshing = false
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateArticleData() {
        // 发起首页文章API请求
        Network.apiService.getReceleView().enqueue(object :Callback<RecycleViewResponse>{
            override fun onResponse(call: Call<RecycleViewResponse>, response: Response<RecycleViewResponse>) {
                if (response.isSuccessful){
                    val receleViewRespose = response.body()?:return
                    val layoutManager = LinearLayoutManager(requireContext())
                    layoutManager.orientation = LinearLayoutManager.VERTICAL // 设置为垂直方向
                    val recyclerView: RecyclerView = binding.recyclerView
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
    private fun updateBannerData(){
        // 发起bannerAPI请求
        Network.apiService.getBanners().enqueue(object : Callback<BannerResponse> {
            override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {
                if (response.isSuccessful) {
                    // 响应成功，获取 Banner 数据
                    val bannerResponse = response.body()?:return
                    for ( bannerItem in bannerResponse.data){
                        // 现在 bannerItem 包含了映射后的数据，将其记录到日志
                        Log.d("BannerItem", "Desc: ${bannerItem.desc}")
                        Log.d("BannerItem", "ID: ${bannerItem.id}")
                        Log.d("BannerItem", "Image Path: ${bannerItem.imagePath}")
                        Log.d("BannerItem", "Is Visible: ${bannerItem.isVisible}")
                        Log.d("BannerItem", "Order: ${bannerItem.order}")
                        Log.d("BannerItem", "Title: ${bannerItem.title}")
                        Log.d("BannerItem", "Type: ${bannerItem.type}")
                        Log.d("BannerItem", "URL: ${bannerItem.url}")

                    }
                    // 如果响应体是一个 BannerItem 对象的列表，你可以遍历它并映射成 BannerItem 对象
                    // 现在 bannerItem 包含了映射后的数据

                    binding.banner1.setAdapter(MyBannerAdapter(bannerResponse.data))
                        .addBannerLifecycleObserver(this@TransformFragment)
                        .indicator = CircleIndicator(this@TransformFragment.requireContext())
                } else {
                    // 处理响应失败的情况
                    Toast.makeText(requireContext(), "调用Bannerapi失败", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                // 处理请求失败的情况
                t.printStackTrace()
            }
        })
    }

}