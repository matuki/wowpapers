package com.pmatuki.wowpapers.core

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplier
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplyResult
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class WallpaperApplierImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WallpaperApplier<Drawable> {

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
