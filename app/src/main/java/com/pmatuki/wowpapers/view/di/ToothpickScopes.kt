package com.pmatuki.wowpapers.view.di

import com.pmatuki.wowpapers.WowpaperApplication
import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.remote.WallpaperDataSource
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind

object WallpaperListViewModelScope {
    val scope: Scope =
        KTP.openScope(WowpaperApplication.APP_SCOPE)
            .openSubScope(WallpaperListViewModelScope)
            .installModules(WallpaperListViewModule)
}

object WallpaperListViewModule : Module() {
    init {
        bind<WallpaperDataSource>()
        bind<WallpaperMapper>()
    }
}

object DetailViewModelScope {
    val scope: Scope = KTP.openScope(WowpaperApplication.APP_SCOPE)
        .openSubScope(DetailViewModelScope)
        .installModules(DetailViewModelModule)
}

object DetailViewModelModule : Module() {
    init {
        bind<ImageDownloadService>()
        bind<WallpaperService>()
    }
}
