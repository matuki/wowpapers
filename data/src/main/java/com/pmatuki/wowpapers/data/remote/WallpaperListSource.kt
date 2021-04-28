package com.pmatuki.wowpapers.data.remote

import com.pmatuki.wowpapers.domain.Wallpaper

interface WallpaperListSource {

    suspend fun getWallpaperList() : List<Wallpaper>

}
