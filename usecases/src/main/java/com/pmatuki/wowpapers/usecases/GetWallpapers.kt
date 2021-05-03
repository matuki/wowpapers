package com.pmatuki.wowpapers.usecases

import com.pmatuki.wowpapers.data.WallpapersRepository
import com.pmatuki.wowpapers.domain.Wallpaper
import toothpick.InjectConstructor

@InjectConstructor
class GetWallpapers(private val wallpapersRepository: WallpapersRepository) {

    suspend operator fun invoke(): List<Wallpaper> = wallpapersRepository.getWallpaperList()

}
