package com.example.myapplication.Data.UserInfo.banner
import com.google.gson.annotations.SerializedName

//Alt + K json to kotlin data class
data class BannerResponse(
    @SerializedName("data")
    val `data`: List<BannerData>,
    @SerializedName("errorCode")
    val errorCode: Int,
    @SerializedName("errorMsg")
    val errorMsg: String
)

data class BannerData(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imagePath")
    val imagePath: String,
    @SerializedName("isVisible")
    val isVisible: Int,
    @SerializedName("order")
    val order: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("url")
    val url: String
)