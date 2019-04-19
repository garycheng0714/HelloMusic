package com.kkbox.hellomusic

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.google.gson.Gson
import com.kkbox.hellomusic.adapter.AlbumAdapter
import com.kkbox.hellomusic.data.Album
import com.kkbox.hellomusic.data.Track
import com.kkbox.openapideveloper.api.Api
import kotlinx.android.synthetic.main.activity_album.*

class AlbumActivity : BasicActivity() {

    companion object {
        const val ALBUM_ID = "album_id"
        const val ALBUM_NAME = "album_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_album)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        album_recyclerview.layoutManager = LinearLayoutManager(baseContext)

        album_recyclerview.adapter = AlbumAdapter(getAlbumData(), baseContext)
    }

    private fun getAlbumData(): ArrayList<Any> {
        val albumData: ArrayList<Any> = arrayListOf()
        val api = Api(accessToken, "TW", baseContext)
        val albumId = intent.getStringExtra(ALBUM_ID)

        val albumInfo = api.albumFetcher.setAlbumId(albumId).fetchMetadata().get().asJsonObject
        val albumTrackArray = api.albumFetcher.setAlbumId(albumId).fetchTracks().get().getAsJsonArray("data")

        val album = Gson().fromJson<Album>(albumInfo, Album::class.java)

        albumData.add(album)

        for (albumTrack in albumTrackArray) {
            val track = Gson().fromJson<Track>(albumTrack, Track::class.java)
            albumData.add(track)
        }

        return albumData
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = intent.getStringExtra(ALBUM_NAME)

        return super.onCreateOptionsMenu(menu)
    }
}
