package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.AmphibiansApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

class AmphibiansAppContainer : AppContainer {

    // Base Url
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    // ApiService
    private val amphibiansApiService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    // Repository
    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(amphibiansApiService)
    }
}