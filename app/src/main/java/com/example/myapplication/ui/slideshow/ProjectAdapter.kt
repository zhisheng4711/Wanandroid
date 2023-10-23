package com.example.myapplication.ui.slideshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Data.UserInfo.RecycleView.LDataX
import com.example.myapplication.R
import com.example.myapplication.ui.transform.WebviewActivity

class ProjectAdapter(private val dataList: List<LDataX>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        // 设置数据到视图上
        holder.textView.text = item.title
        holder.textView2.text = item.author
        holder.textView3.text = item.niceDate

        // 使用Glide加载图像
        Glide.with(holder.itemView.context)
            .load(item.envelopePic)
            .apply(RequestOptions().centerCrop())
            .placeholder(R.mipmap.ic_launcher) // 可选的占位图
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textItem)
        val textView2: TextView = itemView.findViewById(R.id.author)
        val textView3: TextView = itemView.findViewById(R.id.date)
        // 获取ImageView的引用
        val imageView: ImageView = itemView.findViewById(R.id.imageitem)

        private fun watch(){
            // 为列表项视图添加点击事件
            this.itemView.setOnClickListener {
                val context = it.context
                val data = dataList[this.absoluteAdapterPosition]
                // 处理点击事件
                val intent = Intent(context, WebviewActivity::class.java)
                intent.putExtra("url", data.link)
                context.startActivity(intent)
            }
        }
        init {
            watch()
        }
    }
}
