package com.pmatuki.wowpapers.remote.api

import com.pmatuki.wowpapers.remote.model.WallpaperList

interface WallpaperDataSource {

    suspend fun getWallpapers() : WallpaperList

}
