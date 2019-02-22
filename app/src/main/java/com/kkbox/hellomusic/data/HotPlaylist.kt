package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class HotPlaylist(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("images")
    val images: List<CoverImage>,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("owner")
    val owner: HotPlaylistOwner
)