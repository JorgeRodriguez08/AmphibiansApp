package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.AmphibiansApiService

class NetworkAmphibiansRepository (
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians() = amphibiansApiService.getAmphibians()
}
