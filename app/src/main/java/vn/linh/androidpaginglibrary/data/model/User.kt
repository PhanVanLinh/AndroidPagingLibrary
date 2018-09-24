package vn.linh.androidpaginglibrary.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("avatar_url")
    val avatarUrl: String
)