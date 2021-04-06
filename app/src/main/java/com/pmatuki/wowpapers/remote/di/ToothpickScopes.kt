package com.pmatuki.wowpapers.remote.di

import com.pmatuki.wowpapers.remote.api.WallhavenService
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import toothpick.Toothpick
import toothpick.config.Module

object RemoteScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(RemoteModule)
    }
}

object RemoteModule: Module() {
    init {
        bind(WallhavenService::class.java).toInstance(WallhavenService())
    }
}
