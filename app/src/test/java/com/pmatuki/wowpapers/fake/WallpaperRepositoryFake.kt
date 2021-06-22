package com.pmatuki.wowpapers.fake

import com.pmatuki.wowpapers.model.Wallpaper
import com.pmatuki.wowpapers.usecases.get.WallpapersRepository

class WallpaperRepositoryFake(private val wallpaperListSource: WallpaperListSourceFake) :
    WallpapersRepository {

    override suspend fun getWallpaperList(): List<Wallpaper> =
        wallpaperListSource.getWallpaperList()

}