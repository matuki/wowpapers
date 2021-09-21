package com.pmatuki.wowpapers.remote.download

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import com.pmatuki.wowpapers.usecases.download.DownloadResult
import com.pmatuki.wowpapers.usecases.download.WallpaperDownloader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WallpaperDownloaderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WallpaperDownloader {

    override suspend fun performDownload(url: String): DownloadResult {
        return withContext(Dispatchers.IO) {
            try {
                val imageDrawable = Glide.with(context).load(url).submit()
                    .get(DOWNLOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                val downloadedItem = object : WallpaperItemHolder<Drawable> {
                    override val item = imageDrawable
                }
                DownloadResult.Success(downloadedItem)
            } catch (ioe: IOException) {
                DownloadResult.Error(ioe.message)
            }
        }
    }

    companion object {
        const val DOWNLOAD_TIMEOUT_SECONDS = 10L
    }

}
