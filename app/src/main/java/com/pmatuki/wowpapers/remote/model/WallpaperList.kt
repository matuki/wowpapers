package com.pmatuki.wowpapers.remote.model

import com.google.gson.annotations.SerializedName

data class WallpaperList(
    @SerializedName("data")
    val wallpaperList: List<Wallpaper>
)
