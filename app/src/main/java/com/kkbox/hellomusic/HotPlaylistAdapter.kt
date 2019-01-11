package com.kkbox.hellomusic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.hot_playlist_item.view.*

class HotPlaylistAdapter(private val items: ArrayList<String>, private val context: Context)
    : RecyclerView.Adapter<HotPlaylistAdapter.HotPlaylistViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotPlaylistViewHolder {
        return HotPlaylistViewHolder(LayoutInflater.from(context).inflate(R.layout.hot_playlist_item, parent, false), context, items)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: HotPlaylistViewHolder, position: Int) {
        holder.title.text = items[position]
    }

    class HotPlaylistViewHolder (view: View, context: Context, items: ArrayList<String>) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        var title: TextView = view.playlist_title

//        init {
//            title.setOnClickListener {
//                val intent = Intent(context , ThirdActivity::class.java)
//                intent.putExtra(ThirdActivity.TYPE, items[adapterPosition])
//                intent.putExtra(ThirdActivity.POSITION, adapterPosition)
//
//                startActivity(context, intent, null)
//            }
//        }
    }
}

