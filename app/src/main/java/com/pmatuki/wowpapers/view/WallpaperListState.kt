package com.pmatuki.wowpapers.view

import com.pmatuki.wowpapers.view.model.Wallpaper

internal sealed class WallpaperListState {

    object Loading : WallpaperListState()

    object Empty: WallpaperListState()

    data class Loaded(val list: List<Wallpaper>) : WallpaperListState()

    object Error : WallpaperListState()
}
