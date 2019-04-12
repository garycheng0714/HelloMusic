package com.kkbox.hellomusic

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.kkbox.hellomusic.adapter.NewAlbumAdapter
import com.kkbox.hellomusic.data.Album
import com.kkbox.openapideveloper.api.Api
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity: BasicActivity()  {
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    val albumList: ArrayList<Album> = arrayListOf()
    var num:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_album_list)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val gridLayoutManager = GridLayoutManager(baseContext, 2)

        scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                num++
                Log.d("GETALBUM",albumList.size.toString())
                getAllAlbum()
                view.post {
                    // Notify adapter with appropriate notify methods
                    activity_album_list.adapter?.notifyItemRangeInserted(50 * num, 50 * (num+1))
                }
            }
        }

        getAllAlbum()

        activity_album_list.layoutManager = gridLayoutManager

        activity_album_list.adapter = NewAlbumAdapter(albumList, accessToken, baseContext)

        activity_album_list.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    private fun getAllAlbum(): ArrayList<Album> {
        val categoryId = "KrdH2LdyUKS8z2aoxX"

        val api = Api(accessToken, "TW", baseContext)
        val newAlbumResult = api.releaseCategoryFetcher.setCategoryId(categoryId).fetchAlbums(offset= 50 * num).get()
        val albumJsonArray = newAlbumResult.getAsJsonArray("data")

        for (index in 0 until albumJsonArray.size()) {
            val album = Gson().fromJson(albumJsonArray[index], Album::class.java)
            albumList.add(album)
        }

        return albumList
    }
}