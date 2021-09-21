package com.pmatuki.wowpapers.data

import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.model.Wallpaper
import com.pmatuki.wowpapers.usecases.get.WallpapersRepository
import javax.inject.Inject

class WallpapersRepositoryImpl @Inject constructor(
    private val wallpaperListSource: WallpaperListSource
) : WallpapersRepository {

    override suspend fun getWallpaperList(): List<Wallpaper> =
        wallpaperListSource.getWallpaperList()
}
