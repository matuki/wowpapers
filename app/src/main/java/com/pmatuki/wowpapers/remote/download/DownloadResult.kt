package com.pmatuki.wowpapers.remote.download

import android.graphics.drawable.Drawable

sealed class DownloadResult {

    data class Error(val message: String?) : DownloadResult()

    data class Success(val drawable: Drawable) : DownloadResult()

}
