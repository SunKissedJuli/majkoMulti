package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.UserSignIn.UserSignInDataResponse
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignInDataUi

fun UserSignInDataResponse.toUI(): UserSignInDataUi {
    return UserSignInDataUi(
        message = this.message.orEmpty(),
        status = this.status?:0,
        accessToken = this.accessToken.orEmpty()
    )
}