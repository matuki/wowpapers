package com.pmatuki.wowpapers.view.list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.view.common.ProgressBar
import com.pmatuki.wowpapers.view.common.showError
import com.pmatuki.wowpapers.view.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.net.URL

@Composable
internal fun WallpaperListScreen(
    stateFlow: StateFlow<WallpaperListState>,
    eventFlow: Flow<WallpaperListEvent>,
    loadWallpapers: () -> Unit,
    onItemClicked: (wallpaperUrl: String) -> Unit
) {
    val state by stateFlow.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        eventFlow.collect {
            when (it) {
                is WallpaperListEvent.LoadListError -> showError(context, R.string.wallpaper_list_load_fail)
            }
        }
    }

    LaunchedEffect(true) {
        loadWallpapers()
    }

    if (state.loading) {
        Timber.d("mvi - state loading")
        ProgressBar()
    } else {
        Timber.d("mvi - state loaded: ${state.wallpaperList}")
        WallpaperList(state.wallpaperList) { wallpaperId ->
            val wallpaperObj = state.wallpaperList.first { it.id == wallpaperId }
            onItemClicked(wallpaperObj.pathUrl.toString())
        }
    }

//    when (state) {
//        WallpaperListState.Loading -> {
//            ProgressBar()
//        }
//        WallpaperListState.Empty -> {
//            EmptyListText()
//        }
//        WallpaperListState.Error -> {
//            ErrorToast(
//                errorMessageResId = R.string.wallpaper_list_load_fail,
//                snackBarState = scaffoldState.snackbarHostState
//            )
//        }
//        is WallpaperListState.Loaded -> {
//            val wallpaperList = (state as WallpaperListState.Loaded).list
//            WallpaperList(wallpaperList) { wallpaperId ->
//                val wallpaperObj = wallpaperList.first() { it.id == wallpaperId }
//                onItemClicked(wallpaperObj.pathUrl.toString())
//            }
//        }
//    }
}

@Composable
private fun EmptyListText() {
    Text(stringResource(R.string.wallpaper_list_empty))
}

@Composable
private fun WallpaperList(
    wallpaperItems: List<Wallpaper>,
    onWallpaperItemClicked: (wallpaperId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = true,
            applyBottom = true,
            additionalBottom = 24.dp,
            additionalEnd = 24.dp,
            additionalStart = 24.dp,
            additionalTop = 24.dp
        )
    ) {
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.wallpaper_list_title),
                    style = typography.h2
                )
            }
        }

        items(wallpaperItems) { wallpaper ->
            WallpaperCard(wallpaper, onWallpaperItemClicked)
        }
    }
}

@Composable
private fun WallpaperCard(
    wallpaper: Wallpaper,
    onItemClicked: (wallpaperId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onItemClicked(wallpaper.id)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_img_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(wallpaper.thumbUrl.toString()),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(12.dp, 12.dp),
                text = wallpaper.resolution,
                style = typography.subtitle1,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun WallpaperList_Preview() {
    WallpaperList(
        listOf(
            Wallpaper(
                "0",
                "2160x3840",
                URL("https://th.wallhaven.cc/lg/md/md65j8.jpg"),
                URL("https://th.wallhaven.cc/lg/md/md65j8.jpg")
            ),
            Wallpaper(
                "1",
                "2160x3840",
                URL("https://th.wallhaven.cc/lg/md/md65j8.jpg"),
                URL("https://th.wallhaven.cc/lg/md/md65j8.jpg")
            )
        )
    ) { }
}
