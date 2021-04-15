package com.pmatuki.wowpapers

import android.app.Application
import android.content.Context
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind

class WowpaperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KTP.openRootScope().openSubScope(APP_SCOPE).installModules(AppModule(this)).inject(this)
    }

    class AppModule(application: Application) : Module() {
        init {
            val context = application.applicationContext
            bind<Context>().toInstance(context)
        }
    }

    companion object {
        const val APP_SCOPE = "WowpaperApplicationScope"
    }
}
