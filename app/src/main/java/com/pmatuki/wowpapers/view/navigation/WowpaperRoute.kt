package com.pmatuki.wowpapers.view.navigation

import com.pmatuki.wowpapers.usecases.navigate.Route

sealed class WowpaperRoute : Route {

    object List : Route {
        override val destination = "list"
        override var args: Any? = null
        override var clearStack: Boolean = false
    }

    object Detail : Route {
        override val destination = "detail"
        override var args: Any? = null
        override var clearStack: Boolean = false
    }
}
