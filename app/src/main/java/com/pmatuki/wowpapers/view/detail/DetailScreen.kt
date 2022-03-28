package com.pmatuki.wowpapers.view.detail

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.insets.navigationBarsPadding
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.view.common.ProgressBar
import com.pmatuki.wowpapers.view.common.showError
import com.pmatuki.wowpapers.view.extension.collectAsStateLifecycleAware
import com.pmatuki.wowpapers.view.extension.collectLifecycleAware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun DetailScreen(
    stateFlow: StateFlow<DetailViewState>,
    eventFlow: Flow<DetailViewEvent>,
    wallpaperUrl: String,
    downloadWallpaper: (url: String) -> Unit,
    applyWallpaper:(drawable: Drawable) -> Unit
) {
    val state by stateFlow.collectAsStateLifecycleAware()
    val scaffoldState = rememberScaffoldState()
    val activity = LocalContext.current as Activity
    var wallpaperDrawable by remember { mutableStateOf<Drawable?>(null) }
    val context = LocalContext.current

    SideEffect {
        activity.actionBar?.hide()
    }

    LaunchedEffect(wallpaperUrl) {
        downloadWallpaper(wallpaperUrl)
    }

    eventFlow.collectLifecycleAware {
        when (it) {
            is DetailViewEvent.LoadWallpaperError -> showError(context, R.string.image_load_fail)
            is DetailViewEvent.ApplyWallpaperOngoing -> showError(context, R.string.applying_wallpaper_wait)
            is DetailViewEvent.ApplyWallpaperError -> showError(context, R.string.applying_wallpaper_error)
            is DetailViewEvent.ApplyWallpaperSuccess -> showError(context, R.string.applying_wallpaper_success)
        }
    }

    if (state.loading) {
        ProgressBar()
    } else {
        DetailScreenBody(
            wallpaperImage = state.wallpaperItem?.item as Drawable?,
            applyEnabled = state.applying.not(),
            onApplyClicked = applyWallpaper
        )
    }
}

@Composable
private fun DetailScreenBody(
    wallpaperImage: Drawable? = null,
    applyEnabled: Boolean = false,
    onApplyClicked: (wallpaperImage: Drawable) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        val backgroundPainter = if (wallpaperImage != null) {
            rememberDrawablePainter(wallpaperImage)
        } else {
            painterResource(id = R.drawable.ic_img_placeholder)
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = backgroundPainter,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp)
                .navigationBarsPadding(),
            onClick = { if (wallpaperImage != null) onApplyClicked(wallpaperImage) },
            enabled = applyEnabled
        ) {
            Text(text = stringResource(id = R.string.apply_wallpaper_button_text))
        }
    }
}
