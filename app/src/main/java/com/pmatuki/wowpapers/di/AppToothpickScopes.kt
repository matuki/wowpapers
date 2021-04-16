package com.pmatuki.wowpapers.di

import android.app.Application
import android.content.Context
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind


class AppModule(application: Application) : Module() {
    init {
        val context = application.applicationContext
        bind<Context>().toInstance(context)
    }
}

class AppScope(val app: Application) {
    val scope: Scope =
        KTP.openRootScope().openSubScope(APP_SCOPE).installModules(AppModule(app))

    companion object {
        const val APP_SCOPE = "WowpaperApplicationScope"
    }
}

