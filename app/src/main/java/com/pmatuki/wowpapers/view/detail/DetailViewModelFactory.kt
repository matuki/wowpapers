package com.pmatuki.wowpapers.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmatuki.wowpapers.core.WallpaperService
import com.pmatuki.wowpapers.remote.download.ImageDownloadService

class DetailViewModelFactory(
    private val imageDownloadService: ImageDownloadService,
    private val wallpaperService: WallpaperService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(imageDownloadService, wallpaperService) as T
    }
}
