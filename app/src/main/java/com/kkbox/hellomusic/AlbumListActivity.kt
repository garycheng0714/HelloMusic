package com.kkbox.hellomusic

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.kkbox.hellomusic.adapter.NewAlbumAdapter
import com.kkbox.hellomusic.data.Album
import com.kkbox.openapideveloper.api.Api
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity: BasicActivity()  {
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_album_list)
        var rvAlbumList = findViewById<RecyclerView>(R.id.activity_album_list)
        var linearLayoutManager  = LinearLayoutManager(this)
        rvAlbumList.layoutManager = linearLayoutManager

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.d("GETALBUM","new album")
                getAllAlbum()
            }
        }
        // Adds the scroll listener to RecyclerView
        rvAlbumList.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

//        val api = Api(accessToken, "TW", baseContext)
        val gridLayoutManager = GridLayoutManager(baseContext, 2)

        activity_album_list.layoutManager = gridLayoutManager

        activity_album_list.adapter = NewAlbumAdapter(getAllAlbum(), accessToken, baseContext)
    }

    private fun getAllAlbum(): ArrayList<Album> {
        val albumList: ArrayList<Album> = arrayListOf()
        val categoryId = "KrdH2LdyUKS8z2aoxX"

        val api = Api(accessToken, "TW", baseContext)
        val newAlbumResult = api.releaseCategoryFetcher.setCategoryId(categoryId).fetchAlbums().get()
        val albumJsonArray = newAlbumResult.getAsJsonArray("data")

        for (index in 0 until albumJsonArray.size()) {
            val album = Gson().fromJson(albumJsonArray[index], Album::class.java)
            albumList.add(album)
        }

        return albumList
    }
}