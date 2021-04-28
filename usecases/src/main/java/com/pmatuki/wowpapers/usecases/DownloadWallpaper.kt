package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.data.download.DownloadResult
import com.pmatuki.wowpapers.data.download.WallpaperDownloader
import toothpick.InjectConstructor

@InjectConstructor
class DownloadWallpaper(private val downloader: WallpaperDownloader) {

    suspend operator fun invoke(url: String): DownloadResult = downloader.performDownload(url)

}
