package com.pmatuki.wowpapers.view

import android.graphics.drawable.Drawable
import com.pmatuki.wowpapers.view.model.Wallpaper

internal sealed class DetailViewState {

    object Loading : DetailViewState()

    object Empty: DetailViewState()

    data class Loaded(val imageDrawable: Drawable) : DetailViewState()

    data class Error(val message: String?) : DetailViewState()
}
