package com.pmatuki.wowpapers.view

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.pmatuki.wowpapers.databinding.WallpaperItemBinding
import com.pmatuki.wowpapers.view.model.Wallpaper

class WallpaperItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : CardView(context, attrs, defStyleAttr) {

    private lateinit var binding: WallpaperItemBinding

    fun initView(wallpaper: Wallpaper, onClickedCallback: () -> Unit) {
        binding = WallpaperItemBinding.bind(this)
        binding.cardTitle.text = wallpaper.resolution
        //binding.backgroundImage.setImageDrawable()

        setOnClickListener {
            onClickedCallback.invoke()
        }

        setupThumb(wallpaper)
    }

    private fun setupThumb(wallpaper: Wallpaper) {
        // This is probably in the wrong place, but I don't know where to relocate it to without overcomplicating.
        Glide.with(this).load(wallpaper.thumbUrl.toString()).run {
            centerCrop()
            into(binding.backgroundImage)
        }
    }
}
