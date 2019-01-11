package com.kkbox.hellomusic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_hot_playlist.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import com.google.gson.reflect.TypeToken


class HotPlaylistActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with animals
    private val animals: ArrayList<String> = ArrayList()
    private val client = OkHttpClient()
    private val hotPlaylistUrl = "https://api.kkbox.com/v1.1/new-hits-playlists?territory=TW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_playlist)

        getHotPlaylist()

        // Creates a vertical Layout Manager
        hot_playlist_recyclerview.layoutManager = LinearLayoutManager(this)

//        hot_playlist_recyclerview.adapter = HotPlaylistAdapter()
    }

    @Throws(IOException::class)
    fun getJson(url: String): String {
        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Bearer vLnmUrPUt+Z4DIzI1xQ92w==")
            .build()

        val response = client.newCall(request).execute()
        return response.body().toString()
    }

    private fun getHotPlaylist() {
        val gson = Gson()
        val listType = object : TypeToken<ArrayList<HotPlaylist>>() {}.type
        val playlistJsonObjects: ArrayList<HotPlaylist> = gson.fromJson(getJson(hotPlaylistUrl), listType)

        for (jsonObject in playlistJsonObjects) {
            Log.d("Gary", jsonObject.title)
        }
    }
}
