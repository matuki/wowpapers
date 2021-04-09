package com.pmatuki.wowpapers.core

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import com.pmatuki.wowpapers.application.WowpapersApplication
import com.pmatuki.wowpapers.core.di.CoreScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import toothpick.Toothpick
import java.io.IOException
import javax.inject.Inject

class WallpaperService @Inject constructor() {

    suspend fun applyWallpaper(imgDrawable: Drawable) : WallpaperApplyResult =
        withContext(Dispatchers.IO) {
            try {
                WallpaperManager.getInstance(WowpapersApplication.instance).setBitmap(imgDrawable.toBitmap())
                WallpaperApplyResult.Success
            } catch (ioe : IOException) {
                WallpaperApplyResult.Error(ioe.message)
            }
        }
}
