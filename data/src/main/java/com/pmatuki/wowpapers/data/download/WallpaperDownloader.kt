package com.pmatuki.wowpapers.data.download

import com.pmatuki.wowpapers.data.download.DownloadResult

interface WallpaperDownloader {

    suspend fun performDownload(url: String) : DownloadResult
}
