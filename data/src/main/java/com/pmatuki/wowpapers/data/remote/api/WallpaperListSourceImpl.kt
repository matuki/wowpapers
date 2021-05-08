package com.pmatuki.wowpapers.data.remote.api

import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.model.Wallpaper
import toothpick.InjectConstructor

@InjectConstructor
class WallpaperListSourceImpl(val wallhavenService: WallhavenService) : WallpaperListSource {

    override suspend fun getWallpaperList(): List<Wallpaper> =
        wallhavenService.wallApi.searchWallpapers(
            "abstract",
            "9x16,10x16,9x18",
            "favorites"
        ).wallpaperList

}
