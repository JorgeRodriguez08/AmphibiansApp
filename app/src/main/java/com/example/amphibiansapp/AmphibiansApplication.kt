package com.example.amphibiansapp

import android.app.Application
import com.example.amphibiansapp.data.AppContainer
import com.example.amphibiansapp.data.AmphibiansAppContainer

class AmphibiansApplication : Application() {
    // Create variable container
    lateinit var container : AppContainer

    // Create instance of App Container
    override fun onCreate() {
        super.onCreate()
        container = AmphibiansAppContainer()
    }
}