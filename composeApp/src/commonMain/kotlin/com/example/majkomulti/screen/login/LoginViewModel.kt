package com.example.majkomulti.screen.login

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.majkomulti.data.models.UserSignIn.UserSignInData
import com.example.majkomulti.domain.repository.UserRepository
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.domain.manager.AuthManager
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class LoginViewModel : BaseScreenModel<LoginState, LoginEvent>(LoginState.InitState) {
    private val authManager: AuthManager by inject()
    private val userRepository: UserRepository by inject()


    @OptIn(OrbitExperimental::class)
    fun updateLogin(login: String) = blockingIntent {
        reduce {
            state.copy(login = login)
        }
    }
    @OptIn(OrbitExperimental::class)
    fun updatePassword(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)
        }
    }

    fun login(login: String, password: String) = intent {
        launchOperation(
            operation = {
                userRepository.signIn(
                    UserSignInData(login, password)
                )
            },
            success = { clientData ->
                authManager.token = clientData.accessToken
                postSideEffectLocal(LoginEvent.LoginSuccess)
            }
        )
    }

}