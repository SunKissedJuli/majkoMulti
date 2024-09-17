package com.example.majkomulti.data.models.UsrtSignUp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserSignUpDataResponse(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?
)