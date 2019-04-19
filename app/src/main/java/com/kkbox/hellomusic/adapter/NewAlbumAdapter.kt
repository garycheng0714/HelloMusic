package com.kkbox.hellomusic.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.kkbox.hellomusic.AlbumActivity
import com.kkbox.hellomusic.MainActivity
import com.squareup.picasso.Picasso
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.data.Album
import kotlinx.android.synthetic.main.new_album_horizontal_list.view.*
import android.view.ViewGroup
import android.app.ActivityManager

class NewAlbumAdapter(
    private val items: ArrayList<Album>,
    private val accessToken: String,
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
        Picasso.with(context).load(newAlbum.images[2].url).resize(400, 400).into(holder.cover)

        // Get current activity - https://stackoverflow.com/a/12328164
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val cn = am.getRunningTasks(1)[0].topActivity

        if (cn.className.endsWith("AlbumListActivity")) {
            val layoutParams = holder.cardView.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(50, 25, 50, 25)
            holder.cardView.requestLayout()
        }

        holder.title.text = newAlbum.name
        holder.curatorName.text = newAlbum.artist.name

        holder.cover.setOnClickListener {
            val startIntent  = Intent(context, AlbumActivity::class.java).apply {
                putExtra(AlbumActivity.ALBUM_ID, newAlbum.id)
                putExtra(AlbumActivity.ALBUM_NAME, newAlbum.name)
                putExtra(MainActivity.ACCESS_TOKEN, accessToken)
            }

            ContextCompat.startActivity(context, startIntent, null)
        }
    }

    class NewAlbumViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each hot playlist to
        var title: TextView = view.album_title
        var cardView: CardView = view.album_card_view
        var cover: ImageView = view.album_cover
        var curatorName: TextView = view.artist_name
    }
}

