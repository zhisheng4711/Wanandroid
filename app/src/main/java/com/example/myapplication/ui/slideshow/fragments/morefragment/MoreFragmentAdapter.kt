package com.example.myapplication.ui.slideshow.fragments.morefragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.UserInfo.RecycleView.pData
import com.example.myapplication.R

class MoreFragmentAdapter(private val dataList: List<pData>) : RecyclerView.Adapter<MoreFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_more, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        // 设置数据到视图上
        holder.textView.text = item.name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.moretext)

        private fun watch(){
            // 为列表项视图添加点击事件
            this.itemView.setOnClickListener {
                val context = it.context
                val data = dataList[this.absoluteAdapterPosition]
                // 处理点击事件
                val intent = Intent(context, projectlistActivity::class.java)
                intent.putExtra("id", data.id)
                Log.d("传递数据", "传递了: ${data.id}")
                context.startActivity(intent)
            }
        }
        init {
            watch()
        }

    }
}