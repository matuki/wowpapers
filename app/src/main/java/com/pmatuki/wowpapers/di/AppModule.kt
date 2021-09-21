package com.pmatuki.wowpapers.di

import com.pmatuki.wowpapers.data.remote.api.WallhavenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWallhavenService() = WallhavenService()
}