package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class Album(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("explicitness")
    val explicitness: Boolean,

    @SerializedName("available_territories")
    val available_territories: List<String>,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("images")
    val images: List<CoverImage>,

    @SerializedName("artist")
    val artist: Artist
)