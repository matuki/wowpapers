package com.pmatuki.wowpapers.application

import android.app.Application

class WowpapersApplication: Application() {

    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }
}
