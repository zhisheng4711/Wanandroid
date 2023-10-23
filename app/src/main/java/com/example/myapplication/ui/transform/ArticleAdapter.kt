package com.example.myapplication.ui.transform

import DataX
import RecycleViewResponse
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Network
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleAdapter(private val dataList: List<DataX>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_transform, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.newstitle)
        private val authorTextView: TextView = itemView.findViewById(R.id.newsauthor)
        private val niceDateTextView: TextView = itemView.findViewById(R.id.newsdata)


        fun bind(data: DataX) {
            titleTextView.text = data.title
            authorTextView.text = data.author
            niceDateTextView.text = data.niceShareDate
        }

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

    fun update(newData:List<DataX>){
        // 发起首页文章API请求
        Network.apiService.getReceleView().enqueue(object : Callback<RecycleViewResponse> {
            override fun onResponse(call: Call<RecycleViewResponse>, response: Response<RecycleViewResponse>) {
                if (response.isSuccessful){
                    val receleViewRespose = response.body()?:return
                }
            }
            override fun onFailure(call: Call<RecycleViewResponse>, t: Throwable) {
                // 处理请求失败的情况
                t.printStackTrace()
            }
        })

    }



}









