package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpDataResponse
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignUpDataUi

fun UserSignUpDataResponse.toUI(): UserSignUpDataUi {
    return UserSignUpDataUi(
        accessToken = this.accessToken.orEmpty(),
        refreshToken = this.refreshToken.orEmpty()
    )
}