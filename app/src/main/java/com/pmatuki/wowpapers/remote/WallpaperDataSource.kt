package com.pmatuki.wowpapers.remote

import com.pmatuki.wowpapers.remote.api.WallhavenService

class WallpaperDataSource(private val wallhavenService: WallhavenService) {

    suspend fun getWallpapers() = wallhavenService.wallApi.searchWallpapers(
        "abstract",
        "9x16,10x16,9x18",
        "favorites"
    )
}
