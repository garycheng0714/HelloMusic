package com.kkbox.hellomusic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.Album
import kotlinx.android.synthetic.main.new_album_horizontal_list.view.*


class NewAlbumAdapter(
    private val items: ArrayList<Album>,
//    private val listener: OnItemClickListener,
    private val context: Context
): RecyclerView.Adapter<NewAlbumAdapter.NewAlbumViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAlbumViewHolder {
        return NewAlbumViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.new_album_horizontal_list,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: NewAlbumViewHolder, position: Int) {
        val newAlbum = items[position]

        // set image
        Picasso.with(context).load(newAlbum.images[1].url).into(holder.cover)

        val playlistName = newAlbum.name
        val curatorName = newAlbum.artist.name

//        holder.cover.setOnClickListener { listener.onItemClick(newAlbum.id, playlistName) }

        holder.title.text = playlistName
        holder.curatorName.text = curatorName
    }

    class NewAlbumViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each hot playlist to
        var title: TextView = view.album_title
        var cover: ImageView = view.album_cover
        var curatorName: TextView = view.artist_name
    }
}
//
//interface OnItemClickListener {
//    fun onItemClick(playlistId: String, playlistTitle: String)
//}

