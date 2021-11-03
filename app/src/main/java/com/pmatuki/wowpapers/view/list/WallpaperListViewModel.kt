package com.pmatuki.wowpapers.view.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
internal class WallpaperListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getWallpapers: GetWallpapers

    @Inject
    lateinit var wallpaperMapper: WallpaperMapper

    private val _state: MutableStateFlow<WallpaperListState> =
        MutableStateFlow(WallpaperListState.Loading)

    val state: StateFlow<WallpaperListState> = _state

    fun loadWallpapers() = viewModelScope.launch {
        try {
            val wallpaperList = getWallpapers()
            Log.v(
                "Retrofit",
                "API wallpaperDataSource.getWallpapers() returned: $wallpaperList"
            )

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
