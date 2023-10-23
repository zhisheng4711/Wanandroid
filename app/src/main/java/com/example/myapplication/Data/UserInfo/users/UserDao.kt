package com.example.myapplication.Data.UserInfo.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Data.UserInfo.users.User

@Dao
interface UserDao {

    //删除所有数据
    @Query("DELETE FROM users")
    fun deleteAll()

    // 查询并返回所有用户
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    // 根据用户ID数组查询并返回用户列表
    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    // 根据用户的姓名和账号查询用户，限制返回结果为1个
    @Query("SELECT * FROM users WHERE user_name LIKE :first AND " +
            "user_account LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    // 插入一个或多个用户到数据库
    @Insert
    fun insertAll(vararg users: User)

    // 从数据库中删除指定用户
    @Delete
    fun delete(user: User)

    // 根据用户名和账号查找用户（使用协程进行异步操作）
    @Query("SELECT * FROM users WHERE user_name = :username AND user_account = :account")
    suspend fun findUserByUsernameAndAccount(username: String, account: String): User?

    // 根据用户账号查找用户（限制返回结果为1个）
    @Query("SELECT * FROM users WHERE user_account = :account LIMIT 1")
    fun findUserByAccount(account: String): User?

    @Query("SELECT user_name FROM users WHERE user_account = :account")
    fun getNameByAccount(account: String): String
}


