package com.kkbox.hellomusic.data

import com.google.gson.annotations.SerializedName

data class Track (

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("track_number")
    val track_number: Int,

    @SerializedName("explicitness")
    val explicitness: Boolean,

    @SerializedName("available_territories")
    val available_territories: ArrayList<String>,

    @SerializedName("album")
    val album: Album
)