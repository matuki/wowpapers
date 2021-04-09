package com.pmatuki.wowpapers.view.detail

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.core.WallpaperApplyResult
import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.remote.download.DownloadResult
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.view.di.ViewScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

internal class DetailViewModel : ViewModel() {

    @Inject
    lateinit var imageDownloadService: ImageDownloadService

    @Inject
    lateinit var wallpaperService: WallpaperService

    private val _state: MutableLiveData<DetailViewState> =
        MutableLiveData(DetailViewState.Loading)

    val state: LiveData<DetailViewState>
        get() = _state

    init {
        Toothpick.inject(this, ViewScope.scope)
    }

    fun download(imageUrl: String) {
        viewModelScope.launch {
            val result = imageDownloadService.performDownload(imageUrl)
            when (result) {
                is DownloadResult.Success -> _state.value = DetailViewState.Loaded(result.drawable)
                is DownloadResult.Error -> _state.value =
                    DetailViewState.ErrorLoading(result.message)
            }
        }
    }

    fun applyWallpaper(imageDrawable: Drawable) {
        viewModelScope.launch {
            _state.value = DetailViewState.Applying
            val result = wallpaperService.applyWallpaper(imageDrawable)
            when (result) {
                is WallpaperApplyResult.Success -> _state.value = DetailViewState.Applied
                is WallpaperApplyResult.Error -> _state.value =
                    DetailViewState.ErrorApplying(result.message)
            }
        }

    }
}
