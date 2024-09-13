package com.example.majkomulti.screen.register

import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpData
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.repository.UserRepository
import com.example.majkomulti.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce


internal class RegistrationViewModel : BaseScreenModel<RegistrationState, RegistrationEvent>(RegistrationState.InitState) {
    private val authManager: AuthManager by inject()
    private val userRepository: UserRepository by inject()

    fun updateUserName(username : String) = blockingIntent {
        reduce {
            state.copy(name = username)
        }
    }

    fun updateUserPassword(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)
        }
    }

    fun updateUserPasswordRepeat(passwordRepeat: String) = blockingIntent {
        reduce {
            state.copy(passwordRepeat = passwordRepeat)
        }
    }

    fun updateUserLogin(login : String)  = blockingIntent {
        reduce {
            state.copy(login = login)
        }
    }

    fun signUp(user: UserSignUpData) = intent {
        launchOperation(
            operation = {
                userRepository.signUp(
                    user
                )
            },
            success = { clientData ->
                postSideEffectLocal(RegistrationEvent.RegistrationSuccess)
                authManager.token = clientData.accessToken
            },
            failure = {

            }
        )
    }


}