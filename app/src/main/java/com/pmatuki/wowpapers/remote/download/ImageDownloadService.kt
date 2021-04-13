package com.pmatuki.wowpapers.remote.download

import android.content.Context
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import toothpick.InjectConstructor
import java.io.IOException
import java.util.concurrent.TimeUnit

@InjectConstructor
class ImageDownloadService(private val context: Context) {

    suspend fun performDownload(url: String) : DownloadResult {
        return withContext(Dispatchers.IO) {
            try {
                val imageDrawable = Glide.with(context).load(url).submit()
                    .get(DOWNLOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                DownloadResult.Success(imageDrawable)
            } catch (ioe: IOException) {
                DownloadResult.Error(ioe.message)
            }
        }
    }

    companion object {
        const val DOWNLOAD_TIMEOUT_SECONDS = 10L
    }

}
