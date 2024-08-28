package com.example.majkomulti.screen.register

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.platform.Contents.RegisterScreenContent

internal class RegistrationScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { RegistrationViewModel() }
        RegisterScreenContent(viewModel)
    }
}