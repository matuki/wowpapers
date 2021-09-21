package com.pmatuki.wowpapers.usecases.download

class DownloadWallpaper(private val downloader: WallpaperDownloader) {

    suspend operator fun invoke(url: String): DownloadResult = downloader.performDownload(url)

}
