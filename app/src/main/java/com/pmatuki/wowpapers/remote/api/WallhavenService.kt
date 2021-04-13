package com.pmatuki.wowpapers.remote.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class WallhavenService {

    val wallApi: WallhavenApi

    init {
        // Interceptor below is used for additional logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().run {
            baseUrl(WallhavenApi.BASE_URL)
            client(client)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            build()
        }

        wallApi = retrofit.create(WallhavenApi::class.java)
    }
}
