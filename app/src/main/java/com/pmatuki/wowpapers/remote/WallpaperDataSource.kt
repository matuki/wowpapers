package com.pmatuki.wowpapers.remote

import com.pmatuki.wowpapers.remote.api.WallhavenService
import com.pmatuki.wowpapers.remote.di.RemoteScope
import toothpick.Toothpick
import javax.inject.Inject

class WallpaperDataSource @Inject constructor() {

    @Inject
    lateinit var wallhavenService: WallhavenService

    init {
        Toothpick.inject(this, RemoteScope.scope)
    }

    suspend fun getWallpapers() = wallhavenService.wallApi.searchWallpapers(
        "abstract",
        "9x16,10x16,9x18",
        "favorites"
    )
}
