package com.pmatuki.wowpapers.usecases.download

import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder

sealed class DownloadResult {

    data class Error(val message: String?) : DownloadResult()

    data class Success<E : Any>(val item: WallpaperItemHolder<E>) : DownloadResult()

}

