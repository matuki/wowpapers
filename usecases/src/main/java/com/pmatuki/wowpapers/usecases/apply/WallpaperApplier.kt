package com.pmatuki.wowpapers.usecases.apply

interface WallpaperApplier<E> {

    suspend fun applyWallpaper(item: WallpaperItemHolder<E>) : WallpaperApplyResult

}
