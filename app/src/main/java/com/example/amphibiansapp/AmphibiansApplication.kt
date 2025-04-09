package com.example.amphibiansapp

import android.app.Application
import com.example.amphibiansapp.data.AmphibiansAppContainer
import com.example.amphibiansapp.data.AppContainer

class AmphibiansApplication : Application() {
    // Create variable container
    lateinit var container : AppContainer

    // Create instance of App Container
    override fun onCreate() {
        super.onCreate()
        container = AmphibiansAppContainer()
    }
}