package com.kkbox.hellomusic

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.kkbox.hellomusic.adapter.HotPlaylistAdapter
import com.kkbox.hellomusic.data.Album
import com.kkbox.hellomusic.data.HotPlaylist
import com.kkbox.openapideveloper.api.Api
import com.kkbox.openapideveloper.auth.Auth
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object {
        const val ACCESS_TOKEN = "access_token"
        private const val CLIENT_ID: String = "cca0265a5f2b92c776691cb9cfdd6a37"
        private const val CLIENT_SECRET: String = "e6336cc9e943dc29ba0039f858de9a5b"
    }

    private lateinit var context: Context
    private lateinit var accessToken: String
    private val territories = arrayListOf("TW", "JP", "HK", "SG", "MY")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp)
        }

        context = baseContext
    }

    override fun onResume() {
        super.onResume()

        accessToken = getAccessToken()

//        val gridLayoutManager = GridLayoutManager(context, 2)

        hit_playlist_recyclerview.layoutManager = LinearLayoutManager(context)

        hit_playlist_recyclerview.adapter =
                HotPlaylistAdapter(getData(), accessToken, context)
    }

    private fun getData(): ArrayList<Any> {
        val allData = arrayListOf<Any>()

        allData.add(getNewAlbum())

        for (territory in territories) {
            allData.addAll(getTerritoryHitsPlaylist(territory))
        }

        return allData
    }

    private fun getNewAlbum(): ArrayList<Album> {
        val albumList: ArrayList<Album> = arrayListOf()
        val categoryId = "KrdH2LdyUKS8z2aoxX"

        val api = Api(accessToken, "TW", context)
        val newAlbumResult = api.releaseCategoryFetcher.setCategoryId(categoryId).fetchAlbums(limit = 10).get()
        val albumJsonArray = newAlbumResult.getAsJsonArray("data")

        for (index in 0 until albumJsonArray.size()) {
            val album = Gson().fromJson(albumJsonArray[index], Album::class.java)
            albumList.add(album)
        }

        return albumList
    }

    private fun getTerritoryHitsPlaylist(territory: String): ArrayList<HotPlaylist> {
        val api = Api(accessToken, territory, context)
        val hotPlaylists: ArrayList<HotPlaylist> = arrayListOf()

        val hitsPlaylistResult = api.hitsPlaylistFetcher.fetchAllNewHitsPlaylists().get()
        var hotPlaylistJsonArray = JsonArray()

        try{
            hotPlaylistJsonArray = hitsPlaylistResult.getAsJsonArray("data")
        }catch (e: Exception){
            Log.d("playlistError",e.toString())
        }

        for (index in 0 until hotPlaylistJsonArray.size()) {
            val hotPlaylist = Gson().fromJson(hotPlaylistJsonArray[index], HotPlaylist::class.java)
            hotPlaylists.add(hotPlaylist)
        }

        return hotPlaylists
    }

    private fun getAccessToken(): String {
        val auth = Auth(CLIENT_ID, CLIENT_SECRET, context)
        return auth.clientCredentialsFlow.fetchAccessToken().get().get("access_token").asString
    }
}
