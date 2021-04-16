package com.pmatuki.wowpapers

import android.app.Application
import android.content.Context
import com.pmatuki.wowpapers.di.AppScope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind

class WowpaperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppScope(this).scope.inject(this)
    }

}
