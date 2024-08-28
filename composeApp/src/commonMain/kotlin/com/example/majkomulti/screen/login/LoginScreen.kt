package com.example.majkomulti.screen.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.platform.Contents.LoginScreenContent

internal class LoginScreen: Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { LoginViewModel() }
        LoginScreenContent(viewModel)
    }
}