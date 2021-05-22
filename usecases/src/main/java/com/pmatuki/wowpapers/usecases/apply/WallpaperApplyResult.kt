package com.pmatuki.wowpapers.usecases.apply

sealed class WallpaperApplyResult {

    data class Error(val message: String?) : WallpaperApplyResult()

    object Success : WallpaperApplyResult()
}
