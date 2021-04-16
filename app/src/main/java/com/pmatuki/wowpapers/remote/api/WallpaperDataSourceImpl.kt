package com.pmatuki.wowpapers.remote.api

import com.pmatuki.wowpapers.remote.api.WallhavenService
import toothpick.InjectConstructor

@InjectConstructor
class WallpaperDataSourceImpl(val wallhavenService: WallhavenService) : WallpaperDataSource {

    override suspend fun getWallpapers() = wallhavenService.wallApi.searchWallpapers(
        "abstract",
        "9x16,10x16,9x18",
        "favorites"
    )
}
