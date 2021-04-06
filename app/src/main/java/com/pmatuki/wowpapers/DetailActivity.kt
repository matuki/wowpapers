package com.pmatuki.wowpapers

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pmatuki.wowpapers.databinding.ActivityDetailBinding
import com.pmatuki.wowpapers.view.DetailViewModel
import com.pmatuki.wowpapers.view.DetailViewState
import com.pmatuki.wowpapers.view.extension.showToast
import com.pmatuki.wowpapers.view.model.Wallpaper

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var wallpaperDrawable: Drawable

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getParcelableExtra<Wallpaper>(PARAM_WALLPAPER_OBJ)?.let { wallpaper ->
            bindViewModel()
            viewModel.download(wallpaper.pathUrl.toString())
        }
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.state.observe(this, { state ->
            when (state) {
                DetailViewState.Loading -> {
                    binding.apply {
                        layoutViewLoading.visibility = View.VISIBLE
                        cardView.visibility = View.INVISIBLE
                    }
                }
                DetailViewState.Empty -> {
                    binding.apply {
                        layoutViewLoading.visibility = View.GONE
                        cardView.visibility = View.VISIBLE
                    }
                }
                is DetailViewState.Error -> {
                    binding.apply {
                        this@DetailActivity.showToast(R.string.image_load_fail)
                        layoutViewLoading.visibility = View.GONE
                        cardView.visibility = View.VISIBLE
                    }
                }
                is DetailViewState.Loaded -> {
                    binding.apply {
                        layoutViewLoading.visibility = View.GONE
                        cardView.visibility = View.VISIBLE
                    }
                    wallpaperDrawable = state.imageDrawable
                    this@DetailActivity.binding.backgroundImage.setImageDrawable(wallpaperDrawable)
                }
            }
        })
    }

    companion object {
        const val PARAM_WALLPAPER_OBJ = "param_wallpaper_obj"
    }
}
