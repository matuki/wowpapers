package com.pmatuki.wowpapers.data.download

import com.pmatuki.wowpapers.data.WallpaperItemHolder

sealed class DownloadResult {

    data class Error(val message: String?) : DownloadResult()

    data class Success<E : Any>(val item: WallpaperItemHolder<E>) : DownloadResult()

}

