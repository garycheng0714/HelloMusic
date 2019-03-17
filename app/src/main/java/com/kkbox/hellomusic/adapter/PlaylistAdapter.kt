package com.kkbox.hellomusic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.song_info.view.*

class PlaylistAdapter(
    private var songs: ArrayList<JsonElement>,
    private val context: Context
): RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    fun update (songsArray: ArrayList<JsonElement>){
        songs = songsArray
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.song_info,
                parent,
                false
            )
        )
    }

    class PlaylistViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var songName: TextView = view.song_name
        var albumCover: ImageView = view.album_cover
        var artistName: TextView = view.artist_name
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val song = Gson().fromJson(songs[position], Track::class.java)

        Picasso.with(context).load(song.album.images[0].url).resize(200, 200).into(holder.albumCover)

        holder.songName.text = song.name
        holder.artistName.text = song.album.artist.name
    }
}