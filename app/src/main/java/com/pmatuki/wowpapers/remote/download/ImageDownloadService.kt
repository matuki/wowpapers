package com.pmatuki.wowpapers.remote.download

interface ImageDownloadService {

    suspend fun performDownload(url: String) : DownloadResult

}
