package com.pmatuki.wowpapers.data

import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.domain.Wallpaper
import toothpick.InjectConstructor

@InjectConstructor
class WallpapersRepository(private val wallpaperListSource: WallpaperListSource) {

    suspend fun getWallpaperList(): List<Wallpaper>  = wallpaperListSource.getWallpaperList()

}
