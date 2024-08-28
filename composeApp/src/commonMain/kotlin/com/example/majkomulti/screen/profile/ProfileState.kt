package com.example.majkomulti.screen.profile

import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class ProfileState(
    val userName: String = DEFAULT_STRING,
    val userEmail: String = DEFAULT_STRING,
    val isChangePassword: Boolean = DEFAULT_BOOLEAN,
    val oldPassword: String = DEFAULT_STRING,
    val newPassword: String = DEFAULT_STRING,
    val avatar: String = DEFAULT_STRING,
    val confirmPassword: String = DEFAULT_STRING,
    val isError: Boolean = DEFAULT_BOOLEAN,
    val errorMessage: Int? = null,
    val currentUser: CurrentUserDataUi? = CurrentUserDataUi.empty(),
    val isMessage: Boolean = DEFAULT_BOOLEAN,
    val message: Int? = null
){
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_BOOLEAN = false

        fun InitState() = ProfileState()
    }
}