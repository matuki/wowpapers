package com.pmatuki.wowpapers.view.detail

import android.graphics.drawable.Drawable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.usecases.apply.ApplyWallpaper
import com.pmatuki.wowpapers.usecases.apply.WallpaperApplyResult
import com.pmatuki.wowpapers.usecases.apply.WallpaperItemHolder
import com.pmatuki.wowpapers.usecases.download.DownloadResult
import com.pmatuki.wowpapers.usecases.download.DownloadWallpaper
import com.pmatuki.wowpapers.view.mvi.MviContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @Inject
    lateinit var downloadWallpaper: DownloadWallpaper

    @Inject
    lateinit var applyWallpaper: ApplyWallpaper<Drawable>

    val container = MviContainer<DetailViewState, DetailViewEvent>(viewModelScope, DetailViewState())

    fun download(imageUrl: String) = container.intent {
        reduce { copy(loading = true) }

        when (val result = downloadWallpaper(imageUrl)) {
            is DownloadResult.Success<*> -> reduce { copy(wallpaperItem = result.item) }
            is DownloadResult.Error -> sendEvent(DetailViewEvent.LoadWallpaperError)
        }

        reduce { copy(loading = false) }
    }

    fun apply(imageDrawable: Drawable) = container.intent {
        reduce { copy(applying = true) }

        sendEvent(DetailViewEvent.ApplyWallpaperOngoing)
        val item = object : WallpaperItemHolder<Drawable> {
            override val item = imageDrawable
        }

        when (applyWallpaper(item)) {
            is WallpaperApplyResult.Success -> sendEvent(DetailViewEvent.ApplyWallpaperSuccess)
            is WallpaperApplyResult.Error -> sendEvent(DetailViewEvent.ApplyWallpaperError)
        }
        reduce { copy(applying = false) }
    }
}
