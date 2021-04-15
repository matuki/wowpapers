package com.pmatuki.wowpapers.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmatuki.wowpapers.remote.WallpaperDataSource
import com.pmatuki.wowpapers.view.di.WallpaperListViewModelScope
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import javax.inject.Inject

class WallpaperListViewModelFactory() : ViewModelProvider.Factory {

    @Inject
    lateinit var wallpaperDataSource: WallpaperDataSource

    @Inject
    lateinit var wallpaperMapper: WallpaperMapper

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        WallpaperListViewModelScope.scope.inject(this)
        return WallpaperListViewModel(wallpaperDataSource, wallpaperMapper) as T
    }

}
