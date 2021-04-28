package com.pmatuki.wowpapers.data.remote.model

import com.google.gson.annotations.SerializedName
import com.pmatuki.wowpapers.domain.Wallpaper

data class WallpaperList(
    @SerializedName("data")
    val wallpaperList: List<Wallpaper>
)
