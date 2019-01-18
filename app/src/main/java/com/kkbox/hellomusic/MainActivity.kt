package com.kkbox.hellomusic

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.JsonArray
import com.kkbox.hellomusic.adapter.HotPlaylistAdapter
import com.kkbox.hellomusic.adapter.OnItemClickListener
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = baseContext
    }

    override fun onResume() {
        super.onResume()

        accessToken = getAccessToken()

        val hotPlaylistListener = object : OnItemClickListener {
            override fun onItemClick(playlistId: String) {
                val startIntent  = Intent(context, PlaylistActivity::class.java).apply {
                    putExtra(PlaylistActivity.HOT_PLAYLIST_ID, playlistId)
                    putExtra(ACCESS_TOKEN, accessToken)
                }

                startActivity(startIntent)
            }
        }

        val gridLayoutManager = GridLayoutManager(context, 2)

        hit_playlist_recyclerview.layoutManager = gridLayoutManager

        hit_playlist_recyclerview.adapter =
                HotPlaylistAdapter(getAllHitsPlaylist(), hotPlaylistListener, context)
    }

    private fun getAllHitsPlaylist(): JsonArray {
        val jsonArray = JsonArray()
        val territories = arrayListOf("TW", "JP", "HK", "SG", "MY")
//        val territories = arrayListOf("TW")

        for (territory in territories) {
            jsonArray.addAll(getTerritoryHitsPlaylist(territory))
        }

        return jsonArray
    }

    private fun getTerritoryHitsPlaylist(territory: String): JsonArray {
        val api = Api(accessToken, territory, context)

        val hitsPlaylistResult = api.hitsPlaylistFetcher.fetchAllNewHitsPlaylists().get()
        var data = JsonArray()
        try{
            data = hitsPlaylistResult.getAsJsonArray("data")
        }catch (e: Exception){
            Log.d("playlistError",e.toString())
        }
        return data
    }

    private fun getAccessToken(): String {
        val auth = Auth(CLIENT_ID, CLIENT_SECRET, context)
        return auth.clientCredentialsFlow.fetchAccessToken().get().get("access_token").asString
    }
}
