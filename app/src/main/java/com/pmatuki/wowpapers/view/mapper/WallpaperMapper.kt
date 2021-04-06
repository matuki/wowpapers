package com.pmatuki.wowpapers.view.mapper

import java.net.URL
import javax.inject.Inject
import com.pmatuki.wowpapers.view.model.Wallpaper as ViewWallpaper
import com.pmatuki.wowpapers.remote.model.Wallpaper as RemoteWallpaper

class WallpaperMapper @Inject constructor() {

    fun toView(wallpaper: RemoteWallpaper): ViewWallpaper =
        ViewWallpaper(
            id = wallpaper.id,
            resolution = wallpaper.resolution,
            pathUrl = URL(wallpaper.path),
            thumbUrl = URL(wallpaper.thumbs.large)
        )
}
