package com.pmatuki.wowpapers.view.navigation

import com.pmatuki.wowpapers.usecases.navigate.Navigator
import com.pmatuki.wowpapers.usecases.navigate.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class NavigatorImpl @Inject constructor(): Navigator {

    private val _sharedFlow =
        MutableSharedFlow<Route>(extraBufferCapacity = 1)

    override val sharedFlow = _sharedFlow.asSharedFlow()

    override fun navigateTo(route: Route) {
        _sharedFlow.tryEmit(route)
    }
}
