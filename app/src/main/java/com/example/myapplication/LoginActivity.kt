package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.Data.UserInfo.users.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    // 初始化数据库实例
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val editTextAccount = findViewById<EditText>(R.id.editTextAccount)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)



        // 初始化数据库实例
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users"
        ).build()

        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val account = editTextAccount.text.toString()
            val password = editTextPassword.text.toString()

            // 检查输入字段是否为空
            if (account.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "账号和密码都必须填写", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // 输入字段为空，不继续登录
            }

            // 在协程中执行登录验证
            CoroutineScope(Dispatchers.IO).launch {
                val isValid = isValidUser(account, password)
                val username = findname(account)
                withContext(Dispatchers.Main) {
                    if (isValid) {
                        //将数据传输到个人页面
                        // 创建一个 Intent 对象
                        val data = Intent()

                        // 将 username 和 account 添加到 Intent 中
                        data.putExtra("username", username)
                        data.putExtra("account", account)

                        // 设置结果代码和数据
                        setResult(Activity.RESULT_OK, data)

                        // 结束当前页面
                        finish()

                    } else {
                        Toast.makeText(this@LoginActivity, "登录失败，请检查账号和密码", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private suspend fun isValidUser(account: String, password: String): Boolean {
        val userDao = db.userDao()
        val user = userDao.findUserByAccount(account)

        return user != null && user.userPassword == password
    }

    //通过账号返回用户名
    private suspend fun findname(account: String): String {
        val userDao = db.userDao()
        return userDao.getNameByAccount(account)
    }


}