package com.pmatuki.wowpapers.view.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.remote.WallpaperDataSource
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import kotlinx.coroutines.launch
import java.io.IOException

internal class WallpaperListViewModel(
    private val wallpaperDataSource: WallpaperDataSource,
    private val wallpaperMapper: WallpaperMapper
) : ViewModel() {

    private val _state: MutableLiveData<WallpaperListState> =
        MutableLiveData(WallpaperListState.Loading)

    val state: LiveData<WallpaperListState>
        get() = _state

    fun loadWallpapers() = viewModelScope.launch {
        try {
            val wallpaperListResponse = wallpaperDataSource.getWallpapers()
            Log.v(
                "Retrofit",
                "API wallpaperDataSource.getWallpapers() returned: $wallpaperListResponse"
            )
            val wallpaperList = wallpaperListResponse.wallpaperList

            if (wallpaperList.isNotEmpty()) {
                val mappedItems = wallpaperList.map { item -> wallpaperMapper.toView(item) }
                _state.value = WallpaperListState.Loaded(mappedItems)
            } else {
                _state.value = WallpaperListState.Empty
            }
        } catch (e: IOException) {
            _state.value = WallpaperListState.Error
        }
    }
}
