package com.pmatuki.wowpapers.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmatuki.wowpapers.remote.WallpaperDataSource
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper

class WallpaperListViewModelFactory(
    private val wallpaperDataSource: WallpaperDataSource,
    private val wallpaperMapper: WallpaperMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WallpaperListViewModel(wallpaperDataSource, wallpaperMapper) as T
    }

}
