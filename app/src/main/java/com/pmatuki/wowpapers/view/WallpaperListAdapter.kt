package com.pmatuki.wowpapers.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.view.model.Wallpaper

class WallpaperListAdapter(
    val itemClickListener: WallpaperItemClickListener
) : ListAdapter<Wallpaper, RecyclerView.ViewHolder>(WallpaperDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = WallpaperViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)
        if (holder is WallpaperViewHolder) {
            holder.onBind(item, position)
        }
    }

    inner class WallpaperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: Wallpaper, listIndex: Int) {
            (view as WallpaperItemView).initView(data) {
                itemClickListener.onWallpaperItemClicked(data)
            }
        }
    }
}
