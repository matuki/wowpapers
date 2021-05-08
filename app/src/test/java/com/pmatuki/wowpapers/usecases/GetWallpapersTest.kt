package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.data.WallpapersRepositoryImpl
import com.pmatuki.wowpapers.fake.WallpaperListSourceFake
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test


class GetWallpapersTest {

    private val wallpaperListSourceFake = WallpaperListSourceFake(WALLPAPER_COUNT)

    private val getWallpapers = GetWallpapers(WallpapersRepositoryImpl(wallpaperListSourceFake))

    @ExperimentalCoroutinesApi
    @Test
    fun `test if get wallpapers use case returns expected list`()  = runBlockingTest {
        val wallpaperList = getWallpapers()
        Assert.assertTrue(wallpaperList.count() == WALLPAPER_COUNT)
    }

    companion object {
        const val WALLPAPER_COUNT = 50
    }
}
