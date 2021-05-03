package com.pmatuki.wowpapers.domain

data class Wallpaper(
    var id: String,
    var url: String,
    var category: String,
    var resolution: String,
    var ratio: Float,
    var fileSize: Int,
    var createdAt: String,
    var path: String,
    var thumbs: Thumb
)
