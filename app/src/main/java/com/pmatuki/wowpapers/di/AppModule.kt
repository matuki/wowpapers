package com.pmatuki.wowpapers.di

import com.pmatuki.wowpapers.data.remote.api.WallhavenService
import com.pmatuki.wowpapers.usecases.navigate.Navigator
import com.pmatuki.wowpapers.view.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindNavigator(navigator: NavigatorImpl): Navigator

    companion object {
        @Singleton
        @Provides
        fun provideWallhavenService() = WallhavenService()
    }
}
