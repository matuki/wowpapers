package com.pmatuki.wowpapers.view.list

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmatuki.wowpapers.usecases.get.GetWallpapers
import com.pmatuki.wowpapers.usecases.navigate.Navigator
import com.pmatuki.wowpapers.view.mapper.WallpaperMapper
import com.pmatuki.wowpapers.view.mvi.MviContainer
import com.pmatuki.wowpapers.view.navigation.WowpaperRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
internal class WallpaperListViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    @Inject
    lateinit var getWallpapers: GetWallpapers

    @Inject
    lateinit var wallpaperMapper: WallpaperMapper

    val container = MviContainer<WallpaperListState, WallpaperListEvent>(viewModelScope, WallpaperListState())

    fun loadWallpapers() = container.intent {
        try {
            reduce { copy(loading = true) }
            val wallpaperList = getWallpapers()

            if (wallpaperList.isNotEmpty()) {
                val mappedItems = wallpaperList.map { item -> wallpaperMapper.toView(item) }
                reduce { copy(wallpaperList = mappedItems) }
            } else {
                sendEvent(WallpaperListEvent.LoadListError)
            }
        } catch (e: IOException) {
            sendEvent(WallpaperListEvent.LoadListError)
        } finally {
            reduce { copy(loading = false) }
        }
    }

    fun onItemClicked(wallpaperUrl: String) {
        val route = WowpaperRoute.Detail.apply {
            args = Bundle().apply{ putString("wallpaper_url", wallpaperUrl) }
        }
        navigator.navigateTo(route)
    }
}
