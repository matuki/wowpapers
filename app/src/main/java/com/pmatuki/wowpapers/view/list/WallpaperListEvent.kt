package com.pmatuki.wowpapers.view.list

sealed interface WallpaperListEvent {
    object LoadListError: WallpaperListEvent
}

