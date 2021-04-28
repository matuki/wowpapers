package com.pmatuki.wowpapers.core

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import com.pmatuki.wowpapers.data.core.WallpaperApplier
import com.pmatuki.wowpapers.data.core.WallpaperApplyResult
import com.pmatuki.wowpapers.data.WallpaperItemHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import toothpick.InjectConstructor
import java.io.IOException

@InjectConstructor
class WallpaperApplierImpl(
    private val context: Context) : WallpaperApplier<Drawable> {

    override suspend fun applyWallpaper(itemHolder: WallpaperItemHolder<Drawable>): WallpaperApplyResult =
        withContext(Dispatchers.IO) {
            try {
                WallpaperManager.getInstance(context).setBitmap(itemHolder.item.toBitmap())
                WallpaperApplyResult.Success
            } catch (ioe: IOException) {
                WallpaperApplyResult.Error(ioe.message)
            }
        }

}
