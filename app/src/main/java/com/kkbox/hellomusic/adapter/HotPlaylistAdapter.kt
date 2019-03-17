package com.kkbox.hellomusic.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.Album
import com.kkbox.hellomusic.data.HotPlaylist
import kotlinx.android.synthetic.main.hot_playlist_item_card_view.view.*
import kotlinx.android.synthetic.main.new_album_recycler.view.*


class HotPlaylistAdapter(
    private val items: ArrayList<Any>,
    private val listener: OnItemClickListener,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ALBUM_CARD = 0
        private const val PLAYLIST_CARD = 1
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            ALBUM_CARD -> return NewAlbumViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.new_album_recycler,
                        parent,
                        false
                    )
                )

            PLAYLIST_CARD -> return HotPlaylistViewHolder(
                    LayoutInflater.from(context).inflate(
                    R.layout.hot_playlist_item_card_view,
                    parent,
                    false
                )
            )
        }

        return HotPlaylistViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.hot_playlist_item_card_view,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position] !is HotPlaylist) {
            return ALBUM_CARD
        }

        return PLAYLIST_CARD
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = holder.itemViewType

        when (viewType) {
            ALBUM_CARD -> {

                val newAlbum = items[position] as ArrayList<Album>
                holder as NewAlbumViewHolder

                holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                holder.recyclerView.adapter = NewAlbumAdapter(newAlbum, context)

                holder.title.text = context.resources.getText(R.string.albumRecyclerViewTitle)
            }

            PLAYLIST_CARD -> {
                val hotPlaylist: HotPlaylist = items[position] as HotPlaylist
                holder as HotPlaylistViewHolder

                Picasso.with(context).load(hotPlaylist.images[2].url).resize(400, 400).into(holder.cover)

                val playlistName = hotPlaylist.title.substringBefore("(").trim()
                val curatorName = hotPlaylist.owner.name

                holder.cover.setOnClickListener { listener.onItemClick(hotPlaylist.id, playlistName) }
                holder.title.setOnClickListener { listener.onItemClick(hotPlaylist.id, playlistName) }

                holder.title.text = playlistName
                holder.curatorName.text = curatorName
            }
        }
    }

    class HotPlaylistViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each hot playlist to
        var title: TextView = view.playlist_title
        var cover: ImageView = view.playlist_cover
        var curatorName: TextView = view.curator_name
    }

    class NewAlbumViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.album_recyclerview_title
        var recyclerView: RecyclerView = view.new_album_recyclerview
    }
}

interface OnItemClickListener {
    fun onItemClick(playlistId: String, playlistTitle: String)
}

