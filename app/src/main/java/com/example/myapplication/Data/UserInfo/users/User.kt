package com.example.myapplication.Data.UserInfo.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "user_account") val userAccount: String?,
    @ColumnInfo(name = "user_password") val userPassword: String?
)
