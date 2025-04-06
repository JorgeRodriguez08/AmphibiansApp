package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.amphibiansapp.ui.AmphibiansApp
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AmphibiansApp()
                }
            }
        }

    }
}

