package com.pmatuki.wowpapers.view.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.usecases.GetWallpapers
import com.pmatuki.wowpapers.view.di.WallpaperListViewModelScope
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

internal class WallpaperListViewModel() : ViewModel() {

    @Inject
    lateinit var getWallpapers: GetWallpapers

    @Inject
    lateinit var wallpaperMapper: WallpaperMapper

    private val _state: MutableLiveData<WallpaperListState> =
        MutableLiveData(WallpaperListState.Loading)

    val state: LiveData<WallpaperListState>
        get() = _state

    init {
        WallpaperListViewModelScope.scope.inject(this)
    }

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
