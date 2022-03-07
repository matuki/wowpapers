package com.pmatuki.wowpapers.view.detail

import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder

data class DetailViewState(
    val wallpaperItem: WallpaperItemHolder<*>? = null,
    val loading: Boolean = false,
    val applying: Boolean = false,
)
