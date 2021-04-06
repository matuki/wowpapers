package com.pmatuki.wowpapers.remote.api

import com.pmatuki.wowpapers.remote.model.WallpaperList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallhavenApi {

    @GET("search")
    suspend fun searchWallpapers(
        @Query("q") query: String,
        @Query("ratios") ratios: String,
        @Query("sorting") sorting: String
    ): WallpaperList

    companion object {
        const val BASE_URL = "https://wallhaven.cc/api/v1/"
    }
}
