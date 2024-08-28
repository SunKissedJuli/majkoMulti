package com.example.majkomulti.data.models.UsrtSignUp

import kotlinx.serialization.SerialName

data class UserSignUpDataResponse(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?
)