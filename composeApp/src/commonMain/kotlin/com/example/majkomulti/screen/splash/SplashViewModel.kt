package com.example.majkomulti.screen.splash

import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.repository.UserRepository
import kotlinx.coroutines.delay
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class SplashViewModel(initState: Unit) : BaseScreenModel<Unit, SplashEvent>(initState = Unit) {
    private val authManager: AuthManager by inject()

    fun isAutorize() = intent {
       if(authManager.token!=null){
           postSideEffect(SplashEvent.UserAutorize)
       }
        else{
            postSideEffect(SplashEvent.UserNotAutorize)
       }
    }
}