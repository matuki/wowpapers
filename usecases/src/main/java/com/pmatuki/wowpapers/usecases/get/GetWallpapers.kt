package com.pmatuki.wowpapers.usecases.get

import com.pmatuki.wowpapers.model.Wallpaper

class GetWallpapers(private val wallpapersRepository: WallpapersRepository) {

    suspend operator fun invoke(): List<Wallpaper> = wallpapersRepository.getWallpaperList()

}
