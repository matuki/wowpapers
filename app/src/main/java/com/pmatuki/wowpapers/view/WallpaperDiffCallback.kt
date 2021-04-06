package com.pmatuki.wowpapers.view

import androidx.recyclerview.widget.DiffUtil
import com.pmatuki.wowpapers.view.model.Wallpaper

class WallpaperDiffCallback : DiffUtil.ItemCallback<Wallpaper>() {

    override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
        oldItem == newItem

}
