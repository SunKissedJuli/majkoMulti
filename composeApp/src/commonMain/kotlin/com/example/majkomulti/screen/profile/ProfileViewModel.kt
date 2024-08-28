package com.example.majkomulti.screen.profile

import com.example.majkomulti.data.models.User.UserUpdateEmail
import com.example.majkomulti.data.models.User.UserUpdateName
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.repository.UserRepository
import com.example.majkomulti.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File

internal class ProfileViewModel : BaseScreenModel<ProfileState, Unit>(ProfileState.InitState()) {
    private val authManager: AuthManager by inject()
    private val userRepository: UserRepository by inject()

    fun updateUserName(username: String) = blockingIntent {
        reduce { state.copy(userName = username) }
    }

    fun updateUserEmail(email: String) = blockingIntent {
        reduce { state.copy(userEmail = email) }
    }

    fun changePasswordScreen(){

    }

    fun forgetAccount(){
        authManager.token = null
    }

    fun loadData() = intent {
        launchOperation(
            operation = {
                userRepository.currentUser()
            },
            success = { response ->
                reduceLocal {
                    state.copy( currentUser = response,
                        avatar = response.image,
                        userName = response.name,
                        userEmail = response.email,)
                }
            },
            failure = {
            }
        )
    }

    fun updateEmailData(name: String, email: String) = intent{
        launchOperation(
            operation = {
                userRepository.updateUserEmail(
                    UserUpdateEmail(name, email)
                )
            }
        )
    }

    fun updateNameData(name: String)= intent{
        launchOperation(
            operation = {
                userRepository.updateUserName(
                    UserUpdateName(name)
                )
            }
        )
    }

    fun changePassword(){
    }

}