package com.pmatuki.wowpapers.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.remote.download.ImageDownloadService
import com.pmatuki.wowpapers.view.di.DetailViewModelScope
import javax.inject.Inject

class DetailViewModelFactory() : ViewModelProvider.Factory {

    @Inject
    lateinit var imageDownloadService: ImageDownloadService

    @Inject
    lateinit var wallpaperService: WallpaperService

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        DetailViewModelScope.scope.inject(this)
        return DetailViewModel() as T
    }
}
