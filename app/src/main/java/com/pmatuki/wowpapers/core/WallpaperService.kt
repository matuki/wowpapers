package com.pmatuki.wowpapers.core

import android.graphics.drawable.Drawable

interface WallpaperService {

    suspend fun applyWallpaper(imgDrawable: Drawable) : WallpaperApplyResult

}
