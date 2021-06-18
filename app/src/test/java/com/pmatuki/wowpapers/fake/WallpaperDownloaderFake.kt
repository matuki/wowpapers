package com.pmatuki.wowpapers.fake

import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import com.pmatuki.wowpapers.usecases.download.DownloadResult
import com.pmatuki.wowpapers.usecases.download.WallpaperDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext

class WallpaperDownloaderFake : WallpaperDownloader {

    var state: FakeState = FakeState.Normal

    @ExperimentalCoroutinesApi
    override suspend fun performDownload(url: String): DownloadResult {
        delay(3_000)
        return when (state) {
            FakeState.Normal -> {
                val downloadedItem = object : WallpaperItemHolder<ByteArray> {
                    override val item = ByteArray(1024)
                }
                DownloadResult.Success(downloadedItem)
            }
            FakeState.ThrowError -> {
                DownloadResult.Error("Error downloading")
            }
            else -> {
                DownloadResult.Error("Unexpected error")
            }
        }
    }
}
