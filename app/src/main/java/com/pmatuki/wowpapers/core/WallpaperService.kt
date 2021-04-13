package com.pmatuki.wowpapers.core

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class WallpaperService(private val context: Context) {

    suspend fun applyWallpaper(imgDrawable: Drawable) : WallpaperApplyResult =
        withContext(Dispatchers.IO) {
            try {
                WallpaperManager.getInstance(context).setBitmap(imgDrawable.toBitmap())
                WallpaperApplyResult.Success
            } catch (ioe : IOException) {
                WallpaperApplyResult.Error(ioe.message)
            }
        }
}
