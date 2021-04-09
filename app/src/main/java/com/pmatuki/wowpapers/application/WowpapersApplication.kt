package com.pmatuki.wowpapers.application

import android.app.Application
import javax.inject.Inject

class WowpapersApplication @Inject constructor() : Application() {

    // Todo: Not the best solution. Need to figure out how to inject application context with
    // Toothpick and replace this.
    companion object {
        lateinit var instance: WowpapersApplication
    }

    init {
        instance = this
    }
}
