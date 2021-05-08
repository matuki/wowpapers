package com.pmatuki.wowpapers.usecases.download

interface WallpaperDownloader {

    suspend fun performDownload(url: String) : DownloadResult
}
