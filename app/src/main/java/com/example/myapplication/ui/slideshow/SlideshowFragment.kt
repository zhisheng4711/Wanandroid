package com.example.myapplication.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentSlideshowBinding
import com.example.myapplication.ui.slideshow.fragments.FirstFragment
import com.example.myapplication.ui.slideshow.fragments.morefragment.MoreFragment
import com.example.myapplication.ui.slideshow.fragments.ThirdFragment
import com.example.myapplication.ui.slideshow.fragments.SecondFragment
import com.google.android.material.tabs.TabLayoutMediator

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // 此属性仅在 onCreateView 和 onDestroyView 之间有效。
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    // 创建三个片段的列表
    private val fragments = listOf(FirstFragment(), SecondFragment(), ThirdFragment(), MoreFragment())

    // 使用懒加载属性创建 Fragment 适配器
    private val fragmentAdapter by lazy { MyFragmentStateAdapter(this, fragments) }

    // 标题列表
    private val titles = listOf("完整项目", "跨平台应用", "资源聚合类","更多")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 通过绑定创建视图
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 获取 ViewPager2 实例
        viewPager = binding.viewPager

        // 创建适配器并将其设置给 ViewPager2
        val adapter = MyFragmentStateAdapter(this, fragments)
        viewPager.adapter = adapter

        // 设置 ViewPager2 的预加载页面数量
        viewPager.offscreenPageLimit = 2

        // 初始化视图
        initView()

        // 设置页面切换监听
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // 当页面选中时触发回调
                // 在这里可以根据需要执行相应的操作
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // 当页面滚动时触发回调
                // 在这里可以根据需要执行相应的操作
            }

            override fun onPageScrollStateChanged(state: Int) {
                // 当页面滚动状态改变时触发回调
                // 在这里可以根据需要执行相应的操作
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 解除绑定
        _binding = null
    }

    // 初始化视图
    private fun initView() {
        val viewPager = binding.viewPager
        viewPager.adapter = fragmentAdapter
        viewPager.offscreenPageLimit = 2

        // 使用 TabLayoutMediator 关联 TabLayout 和 ViewPager2
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}
