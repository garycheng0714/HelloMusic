package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class CoverImage (

    @SerializedName("height")
    val height: String,

    @SerializedName("width")
    val width: String,

    @SerializedName("url")
    val url: String
)