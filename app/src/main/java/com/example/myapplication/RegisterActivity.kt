package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.Data.UserInfo.users.AppDatabase
import com.example.myapplication.Data.UserInfo.users.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 初始化数据库实例
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users"
        ).build()

        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextAccount = findViewById<EditText>(R.id.editTextAccount)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString()
            val account = editTextAccount.text.toString()
            val password = editTextPassword.text.toString()

            // 检查输入字段是否为空
            if (username.isEmpty() || account.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "所有字段都必须填写", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // 输入字段为空，不继续注册
            }

            // 检查密码长度是否小于六位数
            if (password.length < 6) {
                Toast.makeText(this@RegisterActivity, "密码长度必须至少为六位数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // 密码长度不满足要求，不继续注册
            }

            // 检查账号长度是否小于六位数
            if (account.length < 6) {
                Toast.makeText(this@RegisterActivity, "账号长度必须至少为六位数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // 密码长度不满足要求，不继续注册
            }

            // 在协程中执行数据库操作
            CoroutineScope(Dispatchers.IO).launch {
                if (isUserAlreadyRegistered(username, account)) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "用户名或账号已经注册", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val user = User(userName = username, userAccount = account, userPassword = password)
                    insertUserToDatabase(user)

                    //传回账号
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.putExtra("account", account)

                    val message = "注册成功：用户名=$username, 账号=$account, 密码=$password"
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    finish()
                }
            }
        }

    }

    private suspend fun isUserAlreadyRegistered(username: String, account: String): Boolean {
        val existingUser = db.userDao().findUserByUsernameAndAccount(username, account)
        return existingUser != null
    }

    private fun insertUserToDatabase(user: User) {
        db.userDao().insertAll(user)
    }
}