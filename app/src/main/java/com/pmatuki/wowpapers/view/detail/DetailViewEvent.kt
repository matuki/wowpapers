package com.pmatuki.wowpapers.view.detail

sealed interface DetailViewEvent {
    object LoadWallpaperError : DetailViewEvent
    object ApplyWallpaperOngoing: DetailViewEvent
    object ApplyWallpaperSuccess: DetailViewEvent
    object ApplyWallpaperError : DetailViewEvent
}
