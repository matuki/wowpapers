package com.pmatuki.wowpapers.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class Wallpaper(
    var id: String,
    var resolution: String,
    var pathUrl: URL,
    var thumbUrl: URL
) : Parcelable
