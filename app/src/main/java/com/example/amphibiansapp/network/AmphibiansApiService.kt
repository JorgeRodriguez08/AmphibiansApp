package com.example.amphibiansapp.network

import com.example.amphibiansapp.model.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET ("amphibians")
    suspend fun getAmphibians() : List<Amphibian>
}