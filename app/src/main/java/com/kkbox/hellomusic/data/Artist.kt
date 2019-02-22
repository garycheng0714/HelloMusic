package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class Artist(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("images")
    val images: ArrayList<CoverImage>
)