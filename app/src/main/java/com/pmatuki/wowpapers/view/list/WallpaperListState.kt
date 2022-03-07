package com.pmatuki.wowpapers.view.list

import com.pmatuki.wowpapers.view.model.Wallpaper

data class WallpaperListState(
    val wallpaperList: List<Wallpaper> = emptyList(),
    val loading: Boolean = false
)
