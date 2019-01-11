package com.kkbox.hellomusic

import com.google.gson.annotations.SerializedName

data class ResultBean(
    val error: Boolean,
    val results: List<HotPlaylist>
)

data class HotPlaylist(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val images: String,
    val updated_at: String,
    val owner: String
)

//class HotPlaylist {
//
//    @SerializedName("id")
//    val id: String = ""
//
//    @SerializedName("title")
//    val title: String = ""
//
//    @SerializedName("description")
//    val description: String = ""
//
//    @SerializedName("url")
//    val url: String = ""
//
//    @SerializedName("images")
//    val images: String = ""
//
//    @SerializedName("updated_at")
//    val updated_at: String = ""
//
//    @SerializedName("owner")
//    val owner: String = ""
//}