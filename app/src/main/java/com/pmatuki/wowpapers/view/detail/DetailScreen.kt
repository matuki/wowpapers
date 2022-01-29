package com.pmatuki.wowpapers.view.detail

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.WindowManager
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.insets.navigationBarsPadding
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.view.common.ErrorToast
import com.pmatuki.wowpapers.view.common.ProgressBar
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

@Composable
internal fun DetailScreen(
    stateFlow: StateFlow<*>,
    wallpaperUrl: String,
    downloadWallpaper: (url: String) -> Unit,
    applyWallpaper:(drawable: Drawable) -> Unit
) {
    val state by stateFlow.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val activity = LocalContext.current as Activity
    var wallpaperDrawable by remember { mutableStateOf<Drawable?>(null) }

    SideEffect {
        activity.actionBar?.hide()
    }

    LaunchedEffect(wallpaperUrl) {
        downloadWallpaper(wallpaperUrl)
    }

    when (state) {
        DetailViewState.Loading -> {
            ProgressBar()
        }
        DetailViewState.Empty -> {
            // Show nothing
        }
        is DetailViewState.ErrorLoading -> {
            DetailScreenBody(wallpaperImage = null, applyEnabled = false)
            ErrorToast(
                errorMessageResId = R.string.image_load_fail,
                snackBarState = scaffoldState.snackbarHostState
            )
        }
        is DetailViewState.Loaded -> {
            val loadedState = (state as DetailViewState.Loaded)
            wallpaperDrawable = loadedState.drawableHolder.item as Drawable
            DetailScreenBody(wallpaperImage = wallpaperDrawable, applyEnabled = true, onApplyClicked = applyWallpaper)
        }
        DetailViewState.Applying -> {
            ErrorToast(
                errorMessageResId = R.string.applying_wallpaper_wait,
                snackBarState = scaffoldState.snackbarHostState
            )
            DetailScreenBody(wallpaperImage = wallpaperDrawable, applyEnabled = false)
        }
        DetailViewState.Applied -> {
            DetailScreenBody(wallpaperImage = wallpaperDrawable, applyEnabled = true, onApplyClicked = applyWallpaper)
            ErrorToast(
                errorMessageResId = R.string.applying_wallpaper_success,
                snackBarState = scaffoldState.snackbarHostState
            )
        }
        is DetailViewState.ErrorApplying -> {
            DetailScreenBody(wallpaperImage = wallpaperDrawable, applyEnabled = true, onApplyClicked = applyWallpaper)
            ErrorToast(
                errorMessageResId = R.string.applying_wallpaper_error,
                snackBarState = scaffoldState.snackbarHostState
            )
        }
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
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .navigationBarsPadding(),
            onClick = { if (wallpaperImage != null) onApplyClicked(wallpaperImage) },
            enabled = applyEnabled
        ) {
            Text(text = stringResource(id = R.string.apply_wallpaper_button_text))
        }
    }
}
