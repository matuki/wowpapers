package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.data.WallpaperItemHolder
import com.pmatuki.wowpapers.data.core.WallpaperApplier
import com.pmatuki.wowpapers.data.core.WallpaperApplyResult
import toothpick.InjectConstructor

@InjectConstructor
class ApplyWallpaper<E>(private val wallpaperApplier: WallpaperApplier<E>) {

    suspend operator fun invoke(item: WallpaperItemHolder<E>): WallpaperApplyResult =
        wallpaperApplier.applyWallpaper(item)

}
