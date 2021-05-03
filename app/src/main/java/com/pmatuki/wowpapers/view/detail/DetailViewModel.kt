package com.pmatuki.wowpapers.view.detail

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.data.download.DownloadResult
import com.pmatuki.wowpapers.data.core.WallpaperApplyResult
import com.pmatuki.wowpapers.data.WallpaperItemHolder
import com.pmatuki.wowpapers.usecases.ApplyWallpaper
import com.pmatuki.wowpapers.usecases.DownloadWallpaper
import com.pmatuki.wowpapers.view.di.DetailViewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailViewModel() : ViewModel() {

    @Inject
    lateinit var downloadWallpaper: DownloadWallpaper

    @Inject
    lateinit var applyWallpaper: ApplyWallpaper<Drawable>

    private val _state: MutableLiveData<DetailViewState> =
        MutableLiveData(DetailViewState.Loading)

    val state: LiveData<DetailViewState>
        get() = _state

    init {
        DetailViewModelScope.scope.inject(this)
    }

    fun download(imageUrl: String) {
        viewModelScope.launch {
            when (val result = downloadWallpaper(imageUrl)) {
                is DownloadResult.Success<*> -> _state.value =
                    DetailViewState.Loaded(result.item as WallpaperItemHolder<*>)
                is DownloadResult.Error -> _state.value =
                    DetailViewState.ErrorLoading(result.message)
                else -> Log.w(DetailViewModel::class.simpleName, "Unexpected download result type.")
            }
        }
    }

    fun apply(imageDrawable: Drawable) {
        viewModelScope.launch {
            _state.value = DetailViewState.Applying
            val item = object : WallpaperItemHolder<Drawable> {
                override val item = imageDrawable
            }

            when (val result = applyWallpaper(item)) {
                is WallpaperApplyResult.Success -> _state.value = DetailViewState.Applied
                is WallpaperApplyResult.Error -> _state.value =
                    DetailViewState.ErrorApplying(result.message)
            }
        }

    }
}
