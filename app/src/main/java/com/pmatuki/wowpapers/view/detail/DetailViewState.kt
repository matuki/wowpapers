package com.pmatuki.wowpapers.view.detail

import android.graphics.drawable.Drawable
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder

data class DetailViewState(
    val wallpaperItem: WallpaperItemHolder<*>? = null,
    val loading: Boolean = false,
    val applying: Boolean = false,
)


//
//{
//
//    object Loading : DetailViewState()
//
//    object Empty: DetailViewState()
//
//    data class Loaded(val drawableHolder: WallpaperItemHolder<*>) : DetailViewState()
//
//    data class ErrorLoading(val message: String?) : DetailViewState()
//
//    object Applying : DetailViewState()
//
//    object Applied : DetailViewState()
//
//    data class ErrorApplying(val message: String?) : DetailViewState()
//}
