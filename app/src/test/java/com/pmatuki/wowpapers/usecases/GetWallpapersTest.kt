package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.fake.WallpaperListSourceFake
import com.pmatuki.wowpapers.fake.WallpaperRepositoryFake
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class GetWallpapersTest {

    private val wallpaperListSourceFake = WallpaperListSourceFake(WALLPAPER_COUNT)

    private val getWallpapers = GetWallpapers(WallpaperRepositoryFake(wallpaperListSourceFake))

    @Test
    fun `test if get wallpapers use case returns expected list`()  = runBlockingTest {
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.count() == WALLPAPER_COUNT)
    }

    @Test(expected = IOException::class)
    fun `test if error is returned`() = runBlockingTest {
        wallpaperListSourceFake.throwError()
        getWallpapers()
    }

    @Test
    fun `test if empty list is returned`() = runBlockingTest {
        wallpaperListSourceFake.returnEmpty()
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.isEmpty())
    }

    companion object {
        const val WALLPAPER_COUNT = 50
    }
}
