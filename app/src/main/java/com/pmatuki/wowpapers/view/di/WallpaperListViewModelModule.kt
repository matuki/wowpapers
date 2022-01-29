package com.pmatuki.wowpapers.view.di

import com.pmatuki.wowpapers.data.WallpapersRepositoryImpl
import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.data.remote.api.WallpaperListSourceImpl
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import com.pmatuki.wowpapers.usecases.get.WallpapersRepository
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class WallpaperListViewModelModule {

    @Binds
    abstract fun bindWallpaperRepository(wallpapersRepositoryImpl: WallpapersRepositoryImpl): WallpapersRepository

    @Binds
    abstract fun bindWallpaperListSource(wallpaperListSourceImpl: WallpaperListSourceImpl): WallpaperListSource

    companion object {

        @ViewModelScoped
        @Provides
        fun provideWallpaperMapper() = WallpaperMapper()

        @ViewModelScoped
        @Provides
        fun provideGetWallpapers(repository: WallpapersRepository) = GetWallpapers(repository)
    }
}
