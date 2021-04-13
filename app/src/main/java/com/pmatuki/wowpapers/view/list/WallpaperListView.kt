package com.pmatuki.wowpapers.view.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmatuki.wowpapers.databinding.WallpaperListBinding
import com.pmatuki.wowpapers.view.model.Wallpaper

class WallpaperListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: WallpaperListBinding
    private lateinit var wallpaperListAdapter: WallpaperListAdapter

    fun initView(
        itemClickListener: WallpaperItemClickListener
    ) {
        wallpaperListAdapter = WallpaperListAdapter(itemClickListener)

        binding = WallpaperListBinding.inflate(LayoutInflater.from(context), this, true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = wallpaperListAdapter
    }

    fun updateList(list: List<Wallpaper>) = wallpaperListAdapter.submitList(list)

    fun itemCount() = wallpaperListAdapter.itemCount

}
