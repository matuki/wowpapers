package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.data.WallpapersRepositoryImpl
import com.pmatuki.wowpapers.fake.FakeState
import com.pmatuki.wowpapers.fake.WallpaperApplierFake
import com.pmatuki.wowpapers.fake.WallpaperDownloaderFake
import com.pmatuki.wowpapers.fake.WallpaperListSourceFake
import com.pmatuki.wowpapers.usecases.apply.ApplyWallpaper
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplyResult
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import com.pmatuki.wowpapers.usecases.download.DownloadResult
import com.pmatuki.wowpapers.usecases.download.DownloadWallpaper
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDownloadApplyWallpaperTest {

    private val wallpaperListSourceFake = WallpaperListSourceFake(GetWallpapersTest.WALLPAPER_COUNT)

    private val getWallpapers = GetWallpapers(WallpapersRepositoryImpl(wallpaperListSourceFake))

    private val downloadWallpaper = DownloadWallpaper(WallpaperDownloaderFake())

    private val applyWallpaper = ApplyWallpaper<ByteArray>(WallpaperApplierFake())

    @Test
    fun `test list, download second item and apply successfully`()  = runBlockingTest {
        // List
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.count() == GetWallpapersTest.WALLPAPER_COUNT)

        // Download
        val downloadResult = downloadWallpaper(wallpaperList[2].url)
        Assert.assertTrue(downloadResult is DownloadResult.Success<*>)
        val downloadSuccess = (downloadResult as DownloadResult.Success<*>)
        Assert.assertNotNull(downloadSuccess.item)

        // Apply
        val applyResult = applyWallpaper(downloadSuccess.item as WallpaperItemHolder<ByteArray>)
        Assert.assertTrue(applyResult is WallpaperApplyResult.Success)
    }

    @Test
    fun `test list, error on download fifth item`()  = runBlockingTest {
        // List
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.count() == GetWallpapersTest.WALLPAPER_COUNT)

        // Download with error
        val downloaderFake = WallpaperDownloaderFake().apply { state = FakeState.ThrowError }
        val downloadWallpaperWithError = DownloadWallpaper(downloaderFake)

        val downloadResult = downloadWallpaperWithError(wallpaperList[5].url)
        Assert.assertTrue(downloadResult is DownloadResult.Error)
    }

    @Test
    fun `test list, download last item, error on apply`()  = runBlockingTest {
        // List
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.count() == GetWallpapersTest.WALLPAPER_COUNT)

        // Download
        val downloadResult = downloadWallpaper(wallpaperList[wallpaperList.lastIndex].url)
        Assert.assertTrue(downloadResult is DownloadResult.Success<*>)
        val downloadSuccess = (downloadResult as DownloadResult.Success<*>)
        Assert.assertNotNull(downloadSuccess.item)

        // Apply with error
        val applyWallpaperWithError = ApplyWallpaper(WallpaperApplierFake().apply { doError() })
        val applyResult = applyWallpaperWithError(downloadSuccess.item as WallpaperItemHolder<ByteArray>)
        Assert.assertTrue(applyResult is WallpaperApplyResult.Error)
        Assert.assertEquals((applyResult as WallpaperApplyResult.Error).message, "Error Applying")
    }


}
