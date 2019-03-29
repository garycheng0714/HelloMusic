package com.kkbox.hellomusic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.Album
import com.kkbox.hellomusic.data.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_cover.view.*
import kotlinx.android.synthetic.main.album_info.view.*

class AlbumAdapter(
    private var data: ArrayList<Any>,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ALBUM_COVER = 0
        private const val TRACK = 1
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TRACK -> {
                return TrackViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.album_info,
                        parent,
                        false
                    )
                )
            }

            else -> {
                return AlbumCoverViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.album_cover,
                        parent,
                        false
                    )
                )
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        if(data[position] is Track) {
            return TRACK
        }

        return ALBUM_COVER
    }

    class TrackViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var songName: TextView = view.album_song_name
    }

    class AlbumCoverViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var cover: ImageView = view.album_cover
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TRACK -> {
                holder as TrackViewHolder
                holder.songName.text = "%d. %s".format(position, (data[position] as Track).name)
            }

            else -> {
                val album = data[position] as Album
                holder as AlbumCoverViewHolder
                Picasso.with(context).load(album.images[2].url).into(holder.cover)
            }
        }
    }
}