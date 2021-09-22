package com.pmatuki.wowpapers.view.di

import android.graphics.drawable.Drawable
import com.pmatuki.wowpapers.core.WallpaperApplierImpl
import com.pmatuki.wowpapers.remote.download.WallpaperDownloaderImpl
import com.pmatuki.wowpapers.usecases.apply.ApplyWallpaper
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplier
import com.pmatuki.wowpapers.usecases.download.DownloadWallpaper
import com.pmatuki.wowpapers.usecases.download.WallpaperDownloader
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailViewModelModule {

    @Binds
    abstract fun bindWallPaperDownloader(wallpaperDownloaderImpl: WallpaperDownloaderImpl): WallpaperDownloader

    @Binds
    abstract fun bindWallpaperApplier(wallpaperApplierImpl: WallpaperApplierImpl): WallpaperApplier<Drawable>

    companion object {

        @ViewModelScoped
        @Provides
        fun provideApplyWallpaper(wallpaperApplier: WallpaperApplier<Drawable>) =
            ApplyWallpaper<Drawable>(wallpaperApplier)

        @ViewModelScoped
        @Provides
        fun provideDownloadWallpaper(wallpaperDownloader: WallpaperDownloader) =
            DownloadWallpaper(wallpaperDownloader)
    }
}
