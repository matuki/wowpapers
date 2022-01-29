package com.pmatuki.wowpapers.usecases.navigate

import kotlinx.coroutines.flow.SharedFlow

interface Navigator {

    val sharedFlow: SharedFlow<Route>

    fun navigateTo(route: Route)
}

interface Route {
    val destination: String
    var args: Any?
    var clearStack: Boolean
}
