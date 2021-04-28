package com.pmatuki.wowpapers.data.core

import com.pmatuki.wowpapers.data.WallpaperItemHolder

interface WallpaperApplier<E> {

    suspend fun applyWallpaper(item: WallpaperItemHolder<E>) : WallpaperApplyResult

}
