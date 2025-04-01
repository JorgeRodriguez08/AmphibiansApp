package com.example.amphibiansapp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)

