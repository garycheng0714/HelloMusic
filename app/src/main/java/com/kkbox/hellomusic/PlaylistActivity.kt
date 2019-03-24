package com.kkbox.hellomusic

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kkbox.hellomusic.adapter.PlaylistAdapter
import com.kkbox.openapideveloper.api.Api
import kotlinx.android.synthetic.main.activity_playlist.*
import java.util.*
import org.json.JSONException
import com.google.gson.JsonElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList


class PlaylistActivity : BasicActivity() {

    companion object {
        const val HOT_PLAYLIST_ID    = "hot_playlist_id"
        const val HOT_PLAULIST_TITLE = "hot_playlist_title"
        var sortArray = ArrayList<JsonElement>()
    }

    private lateinit var adapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_playlist)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        sortArray.clear()

        val api = Api(accessToken, "TW", baseContext)
        val playlistId = intent.getStringExtra(HOT_PLAYLIST_ID)

        val tracksResult = api.hitsPlaylistFetcher.setPlaylistId(playlistId).fetchMetadata().get()

//        for(i in 0..(tracksResult.getAsJsonObject("tracks").getAsJsonArray("data").size()-1)){
//            Log.d("trackResult", tracksResult.getAsJsonObject("tracks").getAsJsonArray("data").get(i).toString())
//        }
        playlist_recyclerview.layoutManager = LinearLayoutManager(baseContext)

        val trackArray = tracksResult.getAsJsonObject("tracks").getAsJsonArray("data")
        for (i in 0 until trackArray.size()) {
            try {
                sortArray.add(trackArray[i])
            } catch (e: JSONException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }

        adapter = PlaylistAdapter(
            sortArray,
            baseContext
        )

        playlist_recyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = intent.getStringExtra(HOT_PLAULIST_TITLE)

        return super.onCreateOptionsMenu(menu)
    }

    // https://developer.android.com/training/appbar/actions
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_sort_by_artist -> {
            // sort by artist
            sortByArtist(sortArray)
            adapter.update(sortArray)
            showToast(R.string.sort_by_artist)
            true
        }

        R.id.action_sort_by_date -> {
            // sort by date
            sortByDate(sortArray)
            adapter.update(sortArray)
            showToast(R.string.sort_by_date)
            true
        }

        R.id.action_sort_by_song -> {
            // sort by song
            sortBySongName(sortArray)
            adapter.update(sortArray)
            showToast(R.string.sort_by_song)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    private fun sortBySongName(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                // TODO Auto-generated method stub

                return try {
                    lhs.asJsonObject.get("name").toString().compareTo(rhs.asJsonObject.get("name").toString())
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }

    private fun sortByArtist(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                // TODO Auto-generated method stub
                return try {
                    lhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString().compareTo(rhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString())
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }

    private fun sortByDate(array:ArrayList<JsonElement>){
        Collections.sort(array, object : Comparator<JsonElement> {
            @RequiresApi(Build.VERSION_CODES.O)
            override
            fun compare(lhs: JsonElement, rhs: JsonElement): Int {
                val lhsSongName = lhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString()
                val rhsSongName = rhs.asJsonObject.get("album").asJsonObject.get("artist").asJsonObject.get("name").toString()
                val songNameSort  = lhsSongName > rhsSongName
                val lhsDate = LocalDate.parse(lhs.asJsonObject.get("album").asJsonObject.get("release_date").toString().replace("\"",""), DateTimeFormatter.ISO_DATE)
                val rhsDate = LocalDate.parse(rhs.asJsonObject.get("album").asJsonObject.get("release_date").toString().replace("\"",""), DateTimeFormatter.ISO_DATE)
                var flag: Int
                if(lhsDate.isAfter(rhsDate)){
                    flag = -1
                }else if(lhsDate.isEqual(rhsDate)){
                    flag = if(songNameSort)
                        -1
                    else
                        1
                }else{
                    flag = 1
                }
                // TODO Auto-generated method stub
                return try {
                    flag
                } catch (e: JSONException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    0
                }
            }
        })
    }
}
