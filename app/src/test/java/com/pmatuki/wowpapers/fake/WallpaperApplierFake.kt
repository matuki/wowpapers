package com.pmatuki.wowpapers.fake

import com.pmatuki.wowpapers.usecases.apply.WallpaperApplier
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplyResult
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

class WallpaperApplierFake : WallpaperApplier<ByteArray> {

    private var state: FakeState = FakeState.Normal

    @ExperimentalCoroutinesApi
    override suspend fun applyWallpaper(item: WallpaperItemHolder<ByteArray>): WallpaperApplyResult {
        delay(3_000)
        return when (state) {
            FakeState.Normal -> {
                WallpaperApplyResult.Success
            }
            FakeState.ThrowError -> {
                WallpaperApplyResult.Error("Error Applying")
            }
            else -> {
                WallpaperApplyResult.Error("Unexpected error")
            }
        }
    }

    fun doError() {
        state = FakeState.ThrowError
    }
}
