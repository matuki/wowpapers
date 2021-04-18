package com.pmatuki.wowpapers.view.di

import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.core.WallpaperServiceImpl
import com.pmatuki.wowpapers.di.AppScope
import com.pmatuki.wowpapers.remote.api.WallpaperDataSource
import com.pmatuki.wowpapers.remote.api.WallpaperDataSourceImpl
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.remote.download.ImageDownloadServiceImpl
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind

object WallpaperListViewModelScope {
    val scope: Scope =
        KTP.openScope(AppScope.APP_SCOPE)
            .openSubScope(WallpaperListViewModelScope)
            .installModules(WallpaperListViewModule)
}

object WallpaperListViewModule : Module() {
    init {
        bind<WallpaperDataSource>().toClass<WallpaperDataSourceImpl>()
        bind<WallpaperMapper>()
    }
}

object DetailViewModelScope {
    val scope: Scope = KTP.openScope(AppScope.APP_SCOPE)
        .openSubScope(DetailViewModelScope)
        .installModules(DetailViewModelModule)
}

object DetailViewModelModule : Module() {
    init {
        bind<ImageDownloadService>().toClass<ImageDownloadServiceImpl>()
        bind<WallpaperService>().toClass<WallpaperServiceImpl>()
    }
}
