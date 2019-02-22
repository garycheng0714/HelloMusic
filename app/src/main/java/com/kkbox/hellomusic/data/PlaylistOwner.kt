package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class HotPlaylistOwner (

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("images")
    val images: List<CoverImage>
)