package com.pmatuki.wowpapers.data.remote

import com.pmatuki.wowpapers.model.Wallpaper

interface WallpaperListSource {

    suspend fun getWallpaperList() : List<Wallpaper>

}
