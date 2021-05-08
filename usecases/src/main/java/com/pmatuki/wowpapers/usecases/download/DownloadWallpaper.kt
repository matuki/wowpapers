package com.pmatuki.wowpapers.usecases.download

import toothpick.InjectConstructor

@InjectConstructor
class DownloadWallpaper(private val downloader: WallpaperDownloader) {

    suspend operator fun invoke(url: String): DownloadResult = downloader.performDownload(url)

}
