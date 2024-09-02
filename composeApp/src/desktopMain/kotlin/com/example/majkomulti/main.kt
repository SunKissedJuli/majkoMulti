package com.example.majkomulti

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.majkomulti.di.KoinInjector
import com.example.majkomulti.screen.RootApp.Root
import com.example.majkomulti.strings.MajkoResourceStrings
import java.awt.Dimension

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = MajkoResourceStrings.app_name,
        resizable = true,
        state = rememberWindowState(
            width = 800.dp,
            height = 600.dp,
            placement = WindowPlacement.Floating
        )) {

        window.minimumSize = Dimension(800, 600)
        KoinInjector.koinApp
        Root()
    }
}