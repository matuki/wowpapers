package com.pmatuki.wowpapers.view.mapper

import java.net.URL
import com.pmatuki.wowpapers.view.model.Wallpaper as ViewWallpaper
import com.pmatuki.wowpapers.model.Wallpaper as RemoteWallpaper

class WallpaperMapper {

    fun toView(wallpaper: RemoteWallpaper): ViewWallpaper =
        ViewWallpaper(
            id = wallpaper.id,
            resolution = wallpaper.resolution,
            pathUrl = URL(wallpaper.path),
            thumbUrl = URL(wallpaper.thumbs.large)
        )
}
