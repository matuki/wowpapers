package com.pmatuki.wowpapers.view.detail

import android.graphics.drawable.Drawable

internal sealed class DetailViewState {

    object Loading : DetailViewState()

    object Empty: DetailViewState()

    data class Loaded(val imageDrawable: Drawable) : DetailViewState()

    data class ErrorLoading(val message: String?) : DetailViewState()

    object Applying : DetailViewState()

    object Applied : DetailViewState()

    data class ErrorApplying(val message: String?) : DetailViewState()
}
