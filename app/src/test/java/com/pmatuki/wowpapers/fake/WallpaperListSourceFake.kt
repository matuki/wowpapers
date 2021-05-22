package com.pmatuki.wowpapers.fake

import com.pmatuki.wowpapers.data.remote.WallpaperListSource
import com.pmatuki.wowpapers.model.Thumb
import com.pmatuki.wowpapers.model.Wallpaper
import java.io.IOException

class WallpaperListSourceFake(val wallpaperCount: Int) : WallpaperListSource {

    private var state : FakeState = FakeState.Normal

    override suspend fun getWallpaperList(): List<Wallpaper> =
        when (state) {
            FakeState.Normal -> getFakeWallpaperList(wallpaperCount)
            FakeState.ReturnEmpty -> listOf()
            FakeState.ThrowError -> throw IOException()
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

    fun throwError() {
        state = FakeState.ThrowError
    }

    fun returnEmpty() {
        state = FakeState.ReturnEmpty
    }
}


