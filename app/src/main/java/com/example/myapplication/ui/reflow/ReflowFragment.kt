package com.example.myapplication.ui.reflow

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentReflowBinding
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.LoginActivity
import com.example.myapplication.R


// ReflowFragment类的声明，它扩展自Fragment。
class ReflowFragment : Fragment() {

    // 私有属性，用于保存与该片段布局相关的绑定。
    // 最初将其设置为null。
    private var _binding: FragmentReflowBinding? = null

    // 此属性提供对绑定属性的访问，并确保它仅在onCreateView和onDestroyView生命周期事件之间有效。
    private val binding get() = _binding!!

    companion object {
        const val REQUEST_LOGIN = 1 // 定义一个用于 requestCode 的常量
    }


    // 当片段的UI被创建时，将调用onCreateView方法。
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // 使用ViewModelProvider创建ReflowViewModel的实例。
        val reflowViewModel = ViewModelProvider(this).get(ReflowViewModel::class.java)

        // 使用数据绑定来膨胀片段的布局并获取对绑定对象的引用。
        _binding = FragmentReflowBinding.inflate(inflater, container, false)

        // 获取对膨胀布局的根视图的引用。
        val root: View = binding.root

        val gologinregisterLayout = binding.gologinregister
        val escbutton = binding.escbutton
        // 在根视图上添加点击事件监听器
        gologinregisterLayout.setOnClickListener {
            // 创建一个Intent，用于打开另一个Activity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivityForResult(intent, 1) // 启动目标 Activity 并等待返回结果
            // 如果需要传递数据到目标Activity，可以在这里添加额外的数据
            // intent.putExtra("key", value)

            // 启动目标Activity
            Toast.makeText(requireContext(), "正在打开登录页面...", Toast.LENGTH_SHORT).show()
        }

        escbutton.setOnClickListener {
            // 清除 TextView 上的内容，设置为默认状态
            val textView = requireView().findViewById<TextView>(R.id.tv_fxid)
            val textView1 = requireView().findViewById<TextView>(R.id.tv_name)
            textView.text = "未登录" // 将文本设置为默认状态，可以根据你的需求设置其他默认文本
            textView1.text = "请登录"
        }

        return root
    }

    // 当片段的视图即将被销毁时，将调用onDestroyView方法。
    override fun onDestroyView() {
        super.onDestroyView()

        // 将绑定属性设置为null，以释放对视图的引用。
        _binding = null
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                // 处理登录成功时返回的数据
                val account = data?.getStringExtra("account")
                val username = data?.getStringExtra("username")
                // 在这里使用账号数据进行处理
                val textView = requireView().findViewById<TextView>(R.id.tv_fxid)
                val textView1 = requireView().findViewById<TextView>(R.id.tv_name)
                // 将返回的数据设置到 TextView 上
                textView.text = "账号：$account" // 这里假设你要显示的文本格式为 "账号：XXX"
                textView1.text = "用户名：$username"
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                // 处理用户取消登录的情况
            }
        }
    }
}