package com.pmatuki.wowpapers.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmatuki.wowpapers.view.list.WallpaperListScreen
import com.pmatuki.wowpapers.view.list.WallpaperListViewModel
import com.pmatuki.wowpapers.view.navigation.NavigatorImpl
import com.pmatuki.wowpapers.view.navigation.WowpaperRoute
import com.pmatuki.wowpapers.view.theme.WowpaperTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import android.util.Log
import android.view.WindowManager
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.navOptions
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.usecases.navigate.Navigator
import com.pmatuki.wowpapers.view.detail.DetailScreen
import com.pmatuki.wowpapers.view.detail.DetailViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        window.decorView.setBackgroundColor(getColor(R.color.white))
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            WowpaperTheme {
                ProvideWindowInsets {
                    // Set system bars transparent
                    val systemUiController = rememberSystemUiController()
                    val systemBarsColor = Color.Transparent
                    val useDarkIcons = MaterialTheme.colors.isLight

                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = systemBarsColor,
                            darkIcons = useDarkIcons,
                            isNavigationBarContrastEnforced = true
                        )
                    }

                    Scaffold {
                        NavigationComponent(navController, navigator)
                    }
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        navigator: Navigator
    ) {
        LaunchedEffect(true) {
            navigator.sharedFlow.collect { route ->
                val routeLink = NavDeepLinkRequest
                    .Builder
                    .fromUri(NavDestination.createRoute(route.destination).toUri())
                    .build()
                val routeBundle = route.args as Bundle

                navController.graph.matchDeepLink(routeLink)?.let { deepLinkMatch ->
                    val destination = deepLinkMatch.destination
                    navController.navigate(destination.id, routeBundle, navOptions {
                        if (route.clearStack) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    })
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = WowpaperRoute.List.destination
        ) {
            composable(WowpaperRoute.List.destination) {
                val viewModel = hiltViewModel<WallpaperListViewModel>()
                WallpaperListScreen(viewModel.container.state, viewModel.container.event, viewModel::loadWallpapers, viewModel::onItemClicked)
            }
            composable(WowpaperRoute.Detail.destination) {
                val viewModel = hiltViewModel<DetailViewModel>()
                DetailScreen(
                    viewModel.container.state,
                    viewModel.container.event,
                    it.arguments?.getString("wallpaper_url") ?: "",
                    viewModel::download,
                    viewModel::apply
                )
            }
        }
    }

}
