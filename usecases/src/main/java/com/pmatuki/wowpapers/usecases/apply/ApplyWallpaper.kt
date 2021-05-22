package com.pmatuki.wowpapers.usecases.apply

import toothpick.InjectConstructor

@InjectConstructor
class ApplyWallpaper<E>(private val wallpaperApplier: WallpaperApplier<E>) {

    suspend operator fun invoke(item: WallpaperItemHolder<E>): WallpaperApplyResult =
        wallpaperApplier.applyWallpaper(item)

}
