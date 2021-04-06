package com.pmatuki.wowpapers.remote.download

import com.bumptech.glide.Glide
import com.pmatuki.wowpapers.application.WowpapersApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ImageDownloadService  @Inject constructor() {

    suspend fun performDownload(url: String) : DownloadResult {
        return withContext(Dispatchers.IO) {
            try {
                val imageDrawable = Glide.with(WowpapersApplication.instance).load(url).submit()
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
