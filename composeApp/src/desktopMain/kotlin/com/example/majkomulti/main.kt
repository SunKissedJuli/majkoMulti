package com.example.majkomulti

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.majkomulti.di.KoinInjector
import com.example.majkomulti.screen.RootApp.Root

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Multiplatformmmm",
    ) {

        KoinInjector.koinApp
        Root()

    }
}