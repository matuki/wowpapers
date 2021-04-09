package com.pmatuki.wowpapers.view.di

import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.remote.WallpaperDataSource
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import toothpick.Toothpick
import toothpick.config.Module

object ViewScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(ViewModule)
    }
}

object ViewModule: Module() {
    init {
        bind(WallpaperDataSource::class.java).toInstance(WallpaperDataSource())
        bind(WallpaperMapper::class.java).to(WallpaperMapper::class.java)
        bind(ImageDownloadService::class.java).to(ImageDownloadService::class.java)
        bind (WallpaperService::class.java).to(WallpaperService::class.java)
    }
}
