package com.pmatuki.wowpapers.core

sealed class WallpaperApplyResult {

    data class Error(val message: String?) : WallpaperApplyResult()

    object Success : WallpaperApplyResult()
}
