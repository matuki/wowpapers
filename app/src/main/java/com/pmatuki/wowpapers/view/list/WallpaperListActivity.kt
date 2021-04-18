package com.pmatuki.wowpapers.view.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.databinding.ActivityMainBinding
import com.pmatuki.wowpapers.view.detail.DetailActivity
import com.pmatuki.wowpapers.view.detail.DetailViewModel
import com.pmatuki.wowpapers.view.extension.showToast
import com.pmatuki.wowpapers.view.model.Wallpaper

class WallpaperListActivity : AppCompatActivity(), WallpaperItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: WallpaperListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViewModel()

        binding.listView.initView(this)

        viewModel.loadWallpapers()
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(WallpaperListViewModel::class.java)

        viewModel.state.observe(this, { state ->
            when (state) {
                WallpaperListState.Loading -> {
                    binding.apply {
                        layoutViewLoading.visibility = View.VISIBLE
                        layoutViewEmpty.visibility = View.GONE
                    }
                }
                WallpaperListState.Empty -> {
                    binding.apply {
                        layoutViewLoading.visibility = View.GONE
                        layoutViewEmpty.visibility = View.VISIBLE
                    }
                }
                WallpaperListState.Error -> {
                    binding.apply {
                        this@WallpaperListActivity.showToast(R.string.wallpaper_list_load_fail)
                        layoutViewLoading.visibility = View.GONE
                        layoutViewEmpty.visibility = View.VISIBLE
                    }
                }
                is WallpaperListState.Loaded ->  {
                    binding.apply {
                        layoutViewLoading.visibility = View.GONE
                        layoutViewEmpty.visibility = View.GONE
                    }
                    this@WallpaperListActivity.binding.listView.updateList(state.list)
                }
            }
        })
    }

    override fun onWallpaperItemClicked(wallpaper: Wallpaper) {
        // Not sure if it is ok for this to be here or if should be triggered by ViewModel instead.
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.PARAM_WALLPAPER_OBJ, wallpaper)
        })
    }
}