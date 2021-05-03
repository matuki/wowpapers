package com.pmatuki.wowpapers.view.di

import android.graphics.drawable.Drawable
import com.pmatuki.wowpapers.core.WallpaperApplierImpl
import com.pmatuki.wowpapers.data.core.WallpaperApplier
import com.pmatuki.wowpapers.data.download.WallpaperDownloader
import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.data.remote.api.WallpaperListSourceImpl
import com.pmatuki.wowpapers.data.WallpapersRepository
import com.pmatuki.wowpapers.di.AppScope
import com.pmatuki.wowpapers.remote.download.WallpaperDownloaderImpl
import com.pmatuki.wowpapers.usecases.GetWallpapers
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
        bind<WallpaperMapper>()
        bind<GetWallpapers>()
        bind<WallpapersRepository>()
        bind<WallpaperListSource>().toClass<WallpaperListSourceImpl>()
        // todo: why WallpaperService binding is not needed here since it is a dependency from WallpaperListSourceImpl?
    }
}

object DetailViewModelScope {
    val scope: Scope = KTP.openScope(AppScope.APP_SCOPE)
        .openSubScope(DetailViewModelScope)
        .installModules(DetailViewModelModule)
}

object DetailViewModelModule : Module() {
    init {
        bind<WallpaperDownloader>().toClass<WallpaperDownloaderImpl>()
        bind<WallpaperApplier<Drawable>>().toClass<WallpaperApplierImpl>()
    }
}
