package com.pmatuki.wowpapers.core.di

import com.pmatuki.wowpapers.application.WowpapersApplication
import toothpick.Toothpick
import toothpick.config.Module

object CoreScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(CoreModule(WowpapersApplication.instance))
    }
}

// Todo: Not working. Need to figure out how to properly inject a context using Toothpick
class CoreModule(application: WowpapersApplication): Module() {
    init {
        bind(WowpapersApplication::class.java).toInstance(application)
    }
}
