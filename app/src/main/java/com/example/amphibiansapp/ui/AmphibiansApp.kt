@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.amphibiansapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.R
import com.example.amphibiansapp.ui.screens.AmphibiansScreen
import com.example.amphibiansapp.viewmodel.AmphibiansViewModel


@Composable
fun AmphibiansApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AmphibiansTopAppBar() }
    ) {
            innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            val amphibiansViewModel: AmphibiansViewModel =
                viewModel(factory = AmphibiansViewModel.factory)

            AmphibiansScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = { amphibiansViewModel.getAmphibians() },
                modifier = Modifier.fillMaxSize(),
                contentPadding = innerPadding
            )
        }
    }
}

@Composable
fun AmphibiansTopAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = stringResource(R.string.amphibians),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.background
            )
        }
    )
}

