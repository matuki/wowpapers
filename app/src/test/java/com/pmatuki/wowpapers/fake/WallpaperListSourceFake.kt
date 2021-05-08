package com.pmatuki.wowpapers.fake

import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.model.Thumb
import com.pmatuki.wowpapers.model.Wallpaper

class WallpaperListSourceFake(val wallpaperCount: Int) : WallpaperListSource {

    override suspend fun getWallpaperList(): List<Wallpaper> {
        return getFakeWallpaperList(wallpaperCount)
    }

    private fun getFakeWallpaperList(count: Int): List<Wallpaper> {
        val fakeWallpaperList = mutableListOf<Wallpaper>()
        for (num in 1..count) {
            fakeWallpaperList.add(getFakeWallpaper(num.toString()))
        }
        return fakeWallpaperList.toList()
    }

    private fun getFakeWallpaper(id: String): Wallpaper {
        return Wallpaper(
            id,
            "www.fake.com/wallpaper.png",
            "SomeCategory",
            "800x600",
            1.5f,
            32000,
            "Something",
            "",
            Thumb("", "", "")
        )
    }

}


