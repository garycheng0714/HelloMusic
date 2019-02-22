package com.kkbox.hellomusic.adapter

import android.content.Context
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.view.WindowManager
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.HotPlaylist
import kotlinx.android.synthetic.main.hot_playlist_item_card_view.view.*


class HotPlaylistAdapter(
    private val items: ArrayList<HotPlaylist>,
    private val listener: OnItemClickListener,
    private val context: Context
): RecyclerView.Adapter<HotPlaylistAdapter.HotPlaylistViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotPlaylistViewHolder {
        return HotPlaylistViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.hot_playlist_item_card_view,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: HotPlaylistViewHolder, position: Int) {
        val hotPlaylist = items[position]

        // set image
//        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val size = Point()
//        wm.defaultDisplay.getSize(size)
        Picasso.with(context).load(hotPlaylist.images[2].url).resize(400, 400).into(holder.cover)

        val playlistName = hotPlaylist.title.substringBefore("(").trim()
        val curatorName = hotPlaylist.owner.name

        holder.cover.setOnClickListener { listener.onItemClick(hotPlaylist.id, playlistName) }

        holder.title.text = playlistName
        holder.curatorName.text = curatorName
    }

    class HotPlaylistViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each hot playlist to
        var title: TextView = view.playlist_title
        var cover: ImageView = view.playlist_cover
        var curatorName: TextView = view.curator_name
    }
}

interface OnItemClickListener {
    fun onItemClick(playlistId: String, playlistTitle: String)
}

