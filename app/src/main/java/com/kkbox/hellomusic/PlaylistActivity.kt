package com.kkbox.hellomusic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kkbox.hellomusic.adapter.PlaylistAdapter
import com.kkbox.openapideveloper.api.Api
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {

    companion object {
        const val HOT_PLAYLIST_ID    = "hot_playlist_id"
        const val HOT_PLAULIST_TITLE = "hot_playlist_title"
    }

    private lateinit var accessToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        // https://developer.android.com/training/appbar/setting-up
        setSupportActionBar(findViewById(R.id.toolbar))

        // https://developer.android.com/training/appbar/up-action
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        accessToken = intent.getStringExtra(MainActivity.ACCESS_TOKEN)
    }

    override fun onResume() {
        super.onResume()

        val api = Api(accessToken, "TW", baseContext)
        val playlistId = intent.getStringExtra(HOT_PLAYLIST_ID)

        val tracksResult = api.hitsPlaylistFetcher.setPlaylistId(playlistId).fetchMetadata().get()

        playlist_recyclerview.layoutManager = LinearLayoutManager(baseContext)

        playlist_recyclerview.adapter = PlaylistAdapter(
            tracksResult.getAsJsonObject("tracks").getAsJsonArray("data"),
            baseContext
        )
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
            showToast(R.string.sort_by_artist)
            true
        }

        R.id.action_sort_by_date -> {
            // sort by date
            showToast(R.string.sort_by_date)
            true
        }

        R.id.action_sort_by_song -> {
            // sort by song
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
}
