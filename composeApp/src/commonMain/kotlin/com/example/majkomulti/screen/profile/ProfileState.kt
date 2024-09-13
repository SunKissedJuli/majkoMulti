package com.example.majkomulti.screen.profile

import com.example.majkomulti.commons.Constantas.DEFAULT_BOOLEAN
import com.example.majkomulti.commons.Constantas.DEFAULT_STRING
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class ProfileState(
    val userName: String,
    val userEmail: String,
    val isChangePassword: Boolean,
    val oldPassword: String,
    val newPassword: String,
    val avatar: String,
    val confirmPassword: String,
    val isError: Boolean,
    val errorMessage: Int?,
    val currentUser: CurrentUserDataUi?,
    val isMessage: Boolean,
    val message: Int?
) {
    companion object {

        val InitState = ProfileState(
            userName = DEFAULT_STRING,
            userEmail = DEFAULT_STRING,
            isChangePassword = DEFAULT_BOOLEAN,
            oldPassword = DEFAULT_STRING,
            newPassword = DEFAULT_STRING,
            avatar = DEFAULT_STRING,
            confirmPassword = DEFAULT_STRING,
            isError = DEFAULT_BOOLEAN,
            errorMessage = null,
            currentUser = CurrentUserDataUi.empty(),
            isMessage = DEFAULT_BOOLEAN,
            message = null
        )
    }
}