package com.example.majkomulti.screen.splash

sealed class SplashEvent {

    object UserAutorize: SplashEvent()

    object UserNotAutorize: SplashEvent()

}