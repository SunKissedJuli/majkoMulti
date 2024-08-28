package com.example.majkomulti.screen.RootApp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.majkomulti.screen.splash.SplashScreen
import com.example.majkomulti.theme.MajkoTheme

@Composable
fun Root(){
    MajkoTheme {
        Navigator(SplashScreen()) {
            CompositionLocalProvider(RootNavigator provides it,) {
                CurrentScreen()
            }
        }
    }
}