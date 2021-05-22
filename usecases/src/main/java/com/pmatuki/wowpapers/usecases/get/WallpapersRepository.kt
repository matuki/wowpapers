package com.pmatuki.wowpapers.usecases.get

import com.pmatuki.wowpapers.model.Wallpaper

interface WallpapersRepository {
    suspend fun getWallpaperList(): List<Wallpaper>
}
