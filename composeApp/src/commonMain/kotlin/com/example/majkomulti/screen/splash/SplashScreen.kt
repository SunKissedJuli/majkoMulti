package com.example.majkomulti.screen.splash

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.platform.Contents.SplashScreenContent


class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SplashViewModel(Unit) }
        SplashScreenContent(viewModel)
    }
}

