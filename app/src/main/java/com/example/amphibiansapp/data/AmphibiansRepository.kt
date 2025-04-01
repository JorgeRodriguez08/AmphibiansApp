package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.Amphibian

interface AmphibiansRepository {
    suspend fun getAmphibians() : List<Amphibian>
}