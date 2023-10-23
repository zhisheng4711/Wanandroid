package com.example.myapplication.ui.transform

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Data.UserInfo.banner.BannerData
import com.example.myapplication.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter

class MyBannerAdapter(data: List<BannerData>?) : BannerAdapter<BannerData, MyBannerAdapter.BannerViewHolder>(
    data
) {
    // 定义内部类 BannerViewHolder，继承自 RecyclerView.ViewHolder
    class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {}

    // 创建 ViewHolder 对象
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val context = parent.context    //获取父视图（parent）的上下文（Context）对象，用于后续操作。
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = BannerViewHolder(binding)
        val getData = { mDatas[holder.absoluteAdapterPosition] } // 声明为匿名函数 call，以正确获取 holder.absoluteAdapterPosition

        // 设置点击事件处理逻辑
        binding.root.setOnClickListener {
            // todo , 跳转到 data.url
            Toast.makeText(context, "点了 ${getData().title}", Toast.LENGTH_SHORT).show()
        }

        return holder
    }

    // 绑定数据到 ViewHolder
    override fun onBindView(holder: BannerViewHolder, data: BannerData?, position: Int, size: Int) {
        val data = mDatas[position]
        Glide.with(holder.itemView)
            .load(data.imagePath)
            .into(holder.binding.imageView)
    }
}
